package edu.umb.cs681.hw09;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
	private String name;
	private int size;
	private LocalDateTime creationTime;
	private ApfsDirectory parent;
	public ReentrantLock lock = new ReentrantLock();

	public FSElement(ApfsDirectory parent, String name, int size, LocalDateTime creationTime) {
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;
	}

	public abstract boolean isDirectory();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		lock.lock();
		try {
			return this.size;
		} finally {
			lock.unlock();
		}
	}

	public void setSize(int size) {
		lock.lock();
		try {
			this.size = size;
		} finally {
			lock.unlock();
		}
	}

	public LocalDateTime getCreationTime() {

		lock.lock();
		try {
			return creationTime;
		} finally {
			lock.unlock();
		}
	}

	public void setCreationTime(LocalDateTime createdTime) {

		lock.lock();
		try {
			this.creationTime = createdTime;
		} finally {
			lock.unlock();
		}
	}

	public ApfsDirectory getParent() {

		lock.lock();
		try {
			return parent;
		} finally {
			lock.unlock();
		}
	}

	public void setParent(ApfsDirectory parent) {

		lock.lock();
		try {
			this.parent = parent;
		} finally {
			lock.unlock();
		}
	}

}
