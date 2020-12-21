package edu.umb.cs681.hw18;

import java.util.concurrent.ConcurrentLinkedQueue;

public class DJIAQuoteObservable extends Observable {	
	
	protected float quote;
	public DJIAQuoteObservable() {
		observers = new ConcurrentLinkedQueue<>();
	}

	public float getQuote() {
		return quote;
	}
	public void changeQuote(float q) {
    	this.quote = q;
    	this.setChanged();
    	System.out.print(getClass().getSimpleName() +": ");
    	System.out.println(quote);
    }
	
	public static void main(String[] args) {
		DJIAQuoteObservable djiaquoteObservable = new DJIAQuoteObservable();
		
		System.out.println("Before Quote Change");
		djiaquoteObservable.changeQuote(3000);
		djiaquoteObservable.addObserver( (Observable o, Object obj)->{System.out.println("observerA : " + obj);} );
		djiaquoteObservable.addObserver( (Observable o, Object obj)->{System.out.println("observerB : " + obj);} );
		System.out.println("After Quote Change");
		djiaquoteObservable.changeQuote(40000);
		djiaquoteObservable.notifyObservers(djiaquoteObservable.getQuote());
	}
}