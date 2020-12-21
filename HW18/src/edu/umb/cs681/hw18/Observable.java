package edu.umb.cs681.hw18;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

abstract class Observable {

	private AtomicBoolean changed;
	protected ConcurrentLinkedQueue<Observer> observers;

	public Observable() {
		super();
		observers = new ConcurrentLinkedQueue<>();
		changed = new AtomicBoolean();
	}

	protected void addObserver(Observer o) {
		if (!observers.contains(o)) {

			observers.add(o);

		}
	}

	protected void deleteObserver(Observer o) {

		observers.remove(o);

	}

	protected void deleteObserver() {

		observers.clear();

	}

	protected void setChanged() {
		changed.set(true);
	}

	protected void clearChanged() {
		changed.set(false);
	}

	public boolean hasChanged() {
		return changed.get();
	}

	public void notifyObservers(Object arg) {
		System.out.println("\n" + getClass().getSimpleName() + " Notify Observers: ");
		Object[] localObjs;

		if (!hasChanged())
			return;
		
			localObjs = observers.toArray();
			clearChanged();
		

		for (Object localObj : localObjs) {
			((Observer) localObj).update(this, arg);
		}
	}
}