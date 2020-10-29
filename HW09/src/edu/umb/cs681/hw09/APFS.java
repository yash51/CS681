package edu.umb.cs681.hw09;

import java.time.LocalDateTime;

public class APFS extends FileSystem {

	private static APFS instance = null;

	public static APFS getAPFSFileSystem() {
		if (instance == null)
			instance = new APFS();
		return instance;
	}

	@Override
	protected FSElement createDefaultRoot() {
		// TODO Auto-generated method stub
		LocalDateTime localTime = LocalDateTime.of(2020, 06, 12, 0, 0);
		ApfsDirectory root = new ApfsDirectory(null, "root", 0, localTime, "yash", localTime);
		return root;
	}

	public static void main(String args[]) {
		LocalDateTime localTime = LocalDateTime.of(2020, 06, 12, 0, 0);
		APFS ApfsFileSystem = APFS.getAPFSFileSystem();
		ApfsDirectory root = (ApfsDirectory) ApfsFileSystem.initFileSystem("yashmahant", 5000);
		ApfsDirectory applications = new ApfsDirectory(root, "applications", 0, localTime, "yash", localTime);
		ApfsDirectory home = new ApfsDirectory(root, "home", 0, localTime, "yash", localTime);
		ApfsDirectory code = new ApfsDirectory(home, "code", 0, localTime, "yash", localTime);
		ApfsFile a = new ApfsFile(applications, "a", 100, localTime, "yash", localTime);
		ApfsFile b = new ApfsFile(applications, "b", 100, localTime, "yash", localTime);
		ApfsFile c = new ApfsFile(home, "c", 100, localTime, "yash", localTime);
		ApfsFile d = new ApfsFile(home, "d", 100, localTime, "yash", localTime);
		ApfsFile e = new ApfsFile(code, "e", 100, localTime, "yash", localTime);
		ApfsFile f = new ApfsFile(code, "f", 100, localTime, "guest", localTime);
		ApfsLink x = new ApfsLink(home, "x", 0, localTime, "yash", localTime, applications);
		ApfsLink y = new ApfsLink(code, "y", 0, localTime, "yash", localTime, b);

		APFS ApfsFileSystem1 = APFS.getAPFSFileSystem();
		ApfsDirectory root1 = (ApfsDirectory) ApfsFileSystem1.getRootDirectory();
		System.out.println("root size: " + root1.findDirByName("root").getTotalSize());
		System.out.println("home size: " + root1.findDirByName("home").getTotalSize());
		System.out.println("applications size: " + root1.findDirByName("applications").getTotalSize());
		System.out.println("code size: " + root1.findDirByName("code").getTotalSize());
	}

}