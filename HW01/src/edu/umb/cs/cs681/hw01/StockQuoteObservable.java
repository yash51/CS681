package edu.umb.cs.cs681.hw01;

import java.util.HashMap;
import java.util.LinkedList;

public class StockQuoteObservable extends Observable{

	protected HashMap<String, Float> hMap;

	public StockQuoteObservable() {
		observers = new LinkedList<Observer>();
		hMap = new HashMap<>();
	}
	
	public void changeQuote(String t,float q) {
		this.hMap.put(t,q);
		setChanged();
		System.out.println(getClass().getSimpleName());
		System.out.println(hMap);
	}
	
	public HashMap<String, Float> getHashMap() {
		return hMap;
	}
	
	public static void main(String[] args) {
		StockQuoteObservable stockquoteObservable = new StockQuoteObservable();
		
		System.out.println("Before Quote Change");
		stockquoteObservable.changeQuote("FB", 200);
		stockquoteObservable.addObserver( (Observable o, Object obj)->{System.out.println("A : " + obj);} );
		stockquoteObservable.addObserver( (Observable o, Object obj)->{System.out.println("B : " + obj);} );
		System.out.println("After Quote Change");
		stockquoteObservable.changeQuote("Microsoft", 300);
		stockquoteObservable.notifyObservers(stockquoteObservable.getHashMap());
		stockquoteObservable.changeQuote("Microsoft", 1200);
		stockquoteObservable.notifyObservers(stockquoteObservable.getHashMap());
		
	}
	
}