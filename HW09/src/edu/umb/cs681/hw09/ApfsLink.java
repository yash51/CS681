package edu.umb.cs681.hw09;

import java.time.LocalDateTime;

public class ApfsLink extends ApfsElement {
	private ApfsElement target;

	public ApfsLink(ApfsDirectory parent, String name, int size, LocalDateTime creationTime, String ownerName,
			LocalDateTime lastModified, ApfsElement target) {
		super(parent, name, size, creationTime, ownerName, lastModified);
		// TODO Auto-generated constructor stub
		this.target = target;
		parent.appendChild(this);
	}

	public ApfsElement getTarget() {

		lock.lock();
		try {
			return target;
		} finally {
			lock.unlock();
		}
	}

	public void setTarget(ApfsElement target) {

		lock.lock();
		try {
			this.target = target;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean isDirectory() {
		// TODO Auto-generated method stub
		return false;
	}

}