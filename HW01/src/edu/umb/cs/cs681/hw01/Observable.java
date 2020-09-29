package edu.umb.cs.cs681.hw01;

import java.util.LinkedList;

abstract class Observable {

    protected boolean changed;
    protected LinkedList<Observer> observers;
    
    public Observable() {
		super();
	}

	protected void addObserver(Observer o) {
    	if(!observers.contains(o)) {
    		observers.add(o);
    		}
    	changed = false;
    }

    protected void deleteObserver(Observer o) {
    	observers.remove(o);
    }

    protected void deleteObserver() {
    	observers.removeAll(observers);
    }

    protected void setChanged() {
    	changed = true;
    }
    
    protected void clearChanged() {
    	changed = false;
    }
    
    public boolean hasChanged() {
    	return changed;
    }

    public void notifyObservers(Object arg) {
    	System.out.println("\n" + getClass().getSimpleName() + " Notify Observers: ");
		if(hasChanged()) {
			observers.forEach((Observer observers) -> observers.update(this, arg));
			clearChanged();
		}
    }
}