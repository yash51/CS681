package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount implements BankAccount {
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();

	public void deposit(double amount) {
		lock.lock();
		try {
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + " (d): current balance: " + balance);
			while (balance >= 300) {
				System.out.println(Thread.currentThread().getId() + " (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + " (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public void withdraw(double amount) {
		lock.lock();
		try {
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + " (w): current balance: " + balance);
			while (balance <= 0) {
				System.out.println(Thread.currentThread().getId() + " (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + " (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public static void main(String[] args) {
		ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();
		DepositRunnable depositRunnable = new DepositRunnable(bankAccount);
		WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount);
		Thread[] threads = new Thread[10];
		Thread[] withdrawThreads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			Thread depositThread = new Thread(depositRunnable);
			depositThread.start();
			threads[i] = depositThread;
			Thread withdrawThread = new Thread(withdrawRunnable);
			withdrawThread.start();
			withdrawThreads[i] = withdrawThread;
		}
		try {
			Thread.sleep(500);
		} catch (Exception e) {

		}
		depositRunnable.setDone();
		withdrawRunnable.setDone();
		for (int i = 0; i < 10; i++) {
			threads[i].interrupt();
			withdrawThreads[i].interrupt();
			try {
				threads[i].join();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
