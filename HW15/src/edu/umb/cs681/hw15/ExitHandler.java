package edu.umb.cs681.hw15;

import java.util.concurrent.locks.ReentrantLock;

public class ExitHandler implements Runnable {
	private AdmissionMonitor monitor;
	private volatile boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	
	public ExitHandler(AdmissionMonitor monitor) {
		this.monitor = monitor;
	}

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}

	public void run() {
		while (true) {
			lock.lock();
			try {
				if (done) {
					break;
				}
			} finally {
				lock.unlock();
			}
			monitor.exit();
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				System.out.println(e);
				continue;
			}
		}
	}
}
