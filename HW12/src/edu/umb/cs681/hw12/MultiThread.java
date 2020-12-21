package edu.umb.cs681.hw12;

public class MultiThread implements Runnable {
	private Address address1 = new Address("403 Triveni", "Andheri", "MH", 400069);
	private Address address2 = new Address("400 Ruby", "Borivali", "NH", 400053);

	public void run() {
		Customer customer = new Customer(address1); // 1st customer
		// Trying to set the other address2
		customer.setAddress(address2);
		// Tring to chnage to some other adrress3
		customer.getAddress().change("300 Silver", "Jogeswari", "NY", 400099);
		System.out.println(customer.getAddress());
	}

	public static void main(String[] args) {

		MultiThread m1 = new MultiThread();
		MultiThread m2 = new MultiThread();
		MultiThread m3 = new MultiThread();
		MultiThread m4 = new MultiThread();
		Thread t1 = new Thread(m1);
		t1.start();
		Thread t2 = new Thread(m2);
		t2.start();
		Thread t3 = new Thread(m3);
		t3.start();
		Thread t4 = new Thread(m4);
		t4.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}