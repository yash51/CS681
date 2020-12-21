package edu.umb.cs681.hw16;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {

	protected HashMap<String, Float> hMap;
	private ReentrantLock lock = new ReentrantLock();

	public StockQuoteObservable() {
		observers = new LinkedList<Observer>();
		hMap = new HashMap<>();
	}

	public void changeQuote(String t, float q) {
		lock.lock();
		this.hMap.put(t, q);
		setChanged();
		System.out.println(getClass().getSimpleName());
		System.out.println(hMap);
		lock.unlock();
	}
	
	 public void setQuote() {
		 this.setChanged();
	    }


	public HashMap<String, Float> getHashMap() {
		return hMap;
	}

	public static void main(String[] args) {
		StockQuoteObservable stockquoteObservable = new StockQuoteObservable();

		System.out.println("Before Quote Change");
		stockquoteObservable.changeQuote("FB", 200);
		stockquoteObservable.addObserver((Observable o, Object obj) -> {
			System.out.println("A : " + obj);
		});
		
		
		
		stockquoteObservable.addObserver((Observable o, Object obj) -> {
			System.out.println("B : " + obj);
		});
		System.out.println("After Quote Change");
		stockquoteObservable.changeQuote("Microsoft", 300);
		stockquoteObservable.notifyObservers(stockquoteObservable.getHashMap());
		stockquoteObservable.changeQuote("Microsoft", 1200);
		stockquoteObservable.notifyObservers(stockquoteObservable.getHashMap());
		//ONly for stock class
		Thread t1 = new Thread(()->{
			stockquoteObservable.setQuote();
			stockquoteObservable.notifyObservers(new StockEvent("Bitcoint", (float)18000));
			
		});
		t1.start();
		Thread t2 = new Thread(()->{
			stockquoteObservable.setQuote();
			stockquoteObservable.notifyObservers(new StockEvent("Bitcoint", (float)24000));
			
		});
		
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			t1.join();
			t2.join();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}