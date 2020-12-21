package edu.umb.cs681.hw11;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton implements Runnable {
	private ConcurrentSingleton() {
	};

	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference();

	public static ConcurrentSingleton getInstance() {
		if (instance.get() == null) {
			instance.set(new ConcurrentSingleton());
		}
		return instance.get();
	}

	public void run() {
		System.out.println(ConcurrentSingleton.getInstance());
	}

	public static void main(String[] args) {
		ConcurrentSingleton cc1 = new ConcurrentSingleton();
		ConcurrentSingleton cc2 = new ConcurrentSingleton();
		ConcurrentSingleton cc3 = new ConcurrentSingleton();
		ConcurrentSingleton cc4 = new ConcurrentSingleton();
	
		Thread t1 = new Thread(cc1);
		t1.start();
		Thread t2 = new Thread(cc2);
		t2.start();
		Thread t3 = new Thread(cc3);
		t3.start();
		Thread t4 = new Thread(cc4);
		t4.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}