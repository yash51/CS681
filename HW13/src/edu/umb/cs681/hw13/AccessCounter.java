package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter { // a thread-safe Singleton class.

	// Define a HashMap<java.nio.file.Path, Integer>
	Map<java.nio.file.Path, Integer> hmap = new HashMap<java.nio.file.Path, Integer>();
	// a regular (non-static) lock and use the lock in increment() and getCount()
	private ReentrantLock lock = new ReentrantLock();
	// another (static) lock and use the lock in getInstance()
	private static ReentrantLock staticLock = new ReentrantLock();
	// instance
	private static AccessCounter instance = null;

	public static AccessCounter getInstance() {
		staticLock.lock();
		try {
			if (instance == null) {
				instance = new AccessCounter();
			}
		} finally {
			staticLock.unlock();
		}
		return instance;
	}

	public void increment(Path path) {
		lock.lock();
		try {
			hmap.put(path, hmap.getOrDefault(path, 0) + 1);
		} finally {
			lock.unlock();
		}
	}

	public int getCount(java.nio.file.Path path) {

		lock.lock();
		try {

			return hmap.getOrDefault(path, 0);

		} finally {
			lock.unlock();
		}

	}

}
