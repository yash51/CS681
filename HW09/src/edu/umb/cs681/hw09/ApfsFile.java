package edu.umb.cs681.hw09;

import java.time.LocalDateTime;

public class ApfsFile extends ApfsElement {

	public ApfsFile(ApfsDirectory parent, String name, int size, LocalDateTime createdTime, String ownerName,
			LocalDateTime lastModified) {
		super(parent, name, size, createdTime, ownerName, lastModified);
		// TODO Auto-generated constructor stub
		parent.appendChild(this);
	}

	@Override
	public boolean isDirectory() {
		// TODO Auto-generated method stub
		return false;
	}

}