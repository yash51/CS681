package edu.umb.cs.cs681.hw03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Car {

	private String model, make;
	private int mileage, year, dominationCount;
	private int price;

	public Car(String make, String model, int mileage, int year, int price) {
		super();
		this.model = model;
		this.make = make;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDominationCount() {
		return this.dominationCount;
	}

	public void setDominationCount(ArrayList<Car> cars) {
		int count = 0;
		for (Car car : cars) {
			if (!car.equals(this)) {
				float price = car.getPrice();
				int year = car.getYear();
				int mileage = car.getMileage();

				if (this.getYear() >= year && this.getMileage() <= mileage && this.getPrice() <= price) {
					if (this.getYear() > year || this.getMileage() < mileage || this.getPrice() < price) {
						count++;
					}
				}
			}
		}
		;
		this.dominationCount = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public static void setUp(ArrayList<Car> cars) {
		cars.add(new Car("Tata", "Nano", 200, 2015, 2000));
		cars.add(new Car("Tesla", "3", 30, 2019, 500));
		cars.add(new Car("BMW", "Q5", 300, 2012, 20000));
		cars.add(new Car("Mercedes", "Benz", 0, 2020, 300));
		cars.add(new Car("Ford", "Escatic", 10, 2018, 800));

	}

	static Integer minPrice(ArrayList<Car> cars) {
		return cars.stream().map((Car car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
			if (result == 0)
				return carPrice;
			else if (carPrice < result)
				return carPrice;
			else
				return result;
		});
	}

	static Integer maxPrice(ArrayList<Car> cars) {
		return cars.stream().map((Car car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
			if (result == 0)
				return carPrice;
			else if (carPrice > result)
				return carPrice;
			else
				return result;
		});
	}

	static Integer countOfCars(ArrayList<Car> cars) {
		return cars.stream().map((Car car) -> car.getMake()).reduce(0, (result, make) -> ++result,
				(finalResult, intermidiateResult) -> finalResult);
	}

	public static void main(String args[]) {
		ArrayList<Car> cars = new ArrayList<>();
		setUp(cars);

		cars.forEach((Car car) -> car.setDominationCount(cars));

		Integer minPrice = minPrice(cars);

		System.out.println("Minimum price from cars is: " + minPrice);

		Integer maxPrice = maxPrice(cars);

		System.out.println("Max price from cars is: " + maxPrice);

		Integer numberOfCars = countOfCars(cars);

		System.out.println("Number of cars is: " + numberOfCars);
	}

}
