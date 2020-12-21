package edu.umb.cs681.hw16;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

abstract class Observable {

	private AtomicBoolean changed;
	protected LinkedList<Observer> observers;
	private ReentrantLock guard = new ReentrantLock();

	public Observable() {
		super();
		observers = new LinkedList<Observer>();
		changed = new AtomicBoolean();
	}

	protected void addObserver(Observer o) {
		if (!observers.contains(o)) {
			guard.lock();

			try {
				observers.add(o);
			} finally {
				guard.unlock();
			}

		}
	}

	protected void deleteObserver(Observer o) {

		guard.lock();
		try {
			observers.remove(o);
		} finally {
			guard.unlock();
		}
	}

	protected void deleteObserver() {
		guard.lock();
		try {
			observers.clear();
		} finally {
			guard.unlock();
		}
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
		guard.lock();
		try {
			localObjs = observers.toArray();
			clearChanged();
		} finally {
			guard.unlock();
		}

		for (Object localObj : localObjs) {
			((Observer) localObj).update(this, arg);
		}
	}
}