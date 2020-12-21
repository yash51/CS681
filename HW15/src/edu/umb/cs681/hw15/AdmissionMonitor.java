package edu.umb.cs681.hw15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionMonitor {
	private int currentVisitor = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition belowMaxCondition = lock.newCondition();
	private Condition aboveMinCondition = lock.newCondition();

	public void enter() {
		lock.lock();
		System.out.println("Lock acquired");
		System.out.println(Thread.currentThread().getId() + " (in): Current #visitor: " + currentVisitor);
		while (currentVisitor >= 5) {
			try {
				System.out
						.println(Thread.currentThread().getId() + " (in): Too many visitor. Please wait for a while!");
				belowMaxCondition.await();// waiting until the # of visitors goes below 5
			} catch (InterruptedException e) {
				System.out.println(e.toString());
				return;
			}
		}
		currentVisitor++;
		System.out.println(Thread.currentThread().getId() + " (in): New #visitor: " + currentVisitor);
		aboveMinCondition.signalAll();
		lock.unlock();
		System.out.println("Lock released");
	}

	public void exit() {
		lock.lock();
		System.out.println("Lock acquired");
		System.out.println(Thread.currentThread().getId() + " (out): Current #visitor: " + currentVisitor);
		while (currentVisitor <= 0) {
			try {
				System.out.println(Thread.currentThread().getId() + " (out): There is no visitor inside left!");
				aboveMinCondition.await();
			} catch (InterruptedException e) {
				System.out.println(e.toString());
				return;
			}
		}
		currentVisitor--;
		System.out.println(Thread.currentThread().getId() + " (out): New #visitor: " + currentVisitor);
		belowMaxCondition.signalAll();
		lock.unlock();
		System.out.println("Lock released");
	}

	public int countCurrentVisitor() {
		try {
			lock.lock();
			return currentVisitor;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		AdmissionMonitor admissionMonitor = new AdmissionMonitor();

		EntranceHandler entranceHandler = new EntranceHandler(admissionMonitor);
		ExitHandler exitHandler = new ExitHandler(admissionMonitor);
		Thread[] entranceThreads = new Thread[10];
		Thread[] exitThreads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			Thread entranceThread = new Thread(entranceHandler);
			entranceThread.start();
			entranceThreads[i] = entranceThread;
			Thread exitThread = new Thread(exitHandler);
			exitThread.start();
			exitThreads[i] = exitThread;
		}

		try {
			Thread.sleep(1000);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		entranceHandler.setDone();
		exitHandler.setDone();

		for (int i = 0; i < 10; i++) {
			entranceThreads[i].interrupt();
			exitThreads[i].interrupt();
			try {
				entranceThreads[i].join();
				exitThreads[i].join();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
