package edu.umb.cs681.hw09;

import java.time.LocalDateTime;

public abstract class ApfsElement extends FSElement{
	
	
	private String ownerName;
	private LocalDateTime lastModified;
	
	public String getOwnerName() {
		
		lock.lock();
        try {
        	return ownerName;
        } finally {
            lock.unlock();
        }
	}

	public void setOwnerName(String ownerName) {
		
		lock.lock();
        try {
        	this.ownerName = ownerName;
        } finally {
            lock.unlock();
        }
		
	}

	public LocalDateTime getLastModified() {
		
		lock.lock();
        try {
        	return lastModified;
        } finally {
            lock.unlock();
        }
		
	}

	public void setLastModified(LocalDateTime lastModified) {
		
		lock.lock();
		try {
			this.lastModified = lastModified;
		} finally {
			lock.unlock();
		}
	}

	public ApfsElement(ApfsDirectory parent, String name, int size, LocalDateTime createdTime, String ownerName,
			LocalDateTime lastModified) {
		super(parent, name, size, createdTime);
		this.ownerName = ownerName;
		this.lastModified = lastModified;
	}
	
	public abstract boolean isDirectory();

}