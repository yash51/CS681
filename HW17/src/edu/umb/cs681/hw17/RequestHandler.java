package edu.umb.cs681.hw17;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {

	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	private final static String[] paths = { "1.html", "2.html", "3.html", "4.html", "5.html", "6.html" };

	public RequestHandler(int id) {
	}

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			lock.lock();
			try {
				if (done)
					break;
				Random random = new Random();
				int index = random.nextInt(paths.length);

				Path path = Paths.get(paths[index]);
				AccessCounter.getInstance().increment(path);
			} finally {
				lock.unlock();
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}

	public static void main(String[] args) {

		Thread[] threads = new Thread[20];
		RequestHandler[] requestHandlers = new RequestHandler[20];

		for (int i = 0; i < 20; i++) {
			System.out.println("Starting Thread " + i);
			requestHandlers[i] = new RequestHandler(i);
			threads[i] = new Thread(requestHandlers[i]);
			threads[i].start();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " " + e);
		}

		for (int i = 0; i < 20; i++) {
			requestHandlers[i].setDone();
			threads[i].interrupt();
			try {
				threads[i].join();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		for (String p : paths) {
			Path path = Paths.get(p);
			System.out
					.println(p + " is getting accessed " + AccessCounter.getInstance().getCount(path) + " time/times");
		}
	}

}
