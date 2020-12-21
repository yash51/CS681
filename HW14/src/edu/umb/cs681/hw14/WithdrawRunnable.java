package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable {
	private BankAccount account;
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();

	public WithdrawRunnable(BankAccount account) {
		this.account = account;
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
			account.withdraw(200);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException exception) {
				System.out.println(exception);
				continue;
			}
		}
	}
}
