package com.machineCoding.InMemoryFileSystemSelf;

import com.machineCoding.InMemoryFileSystemSelf.models.File;
import com.machineCoding.InMemoryFileSystemSelf.models.FileSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InMemoryFileSystemSelfApplicationTests {

	@Test
	void contextLoads() {
		FileSystem fileSystem = new FileSystem();
		fileSystem.ls();
		try {
			fileSystem.mkdir("/a/b/c");
		} catch (Exception e) {
			System.out.println(e);
		}
		fileSystem.mkdir("/a");
		fileSystem.mkdir("/a/b");
		fileSystem.mkdir("/a/b/c");
		fileSystem.mkdir("/a/b/c/d");
		fileSystem.mkdir("/a/b/c/e");
		fileSystem.mkdir("/a/b/c/f");
		fileSystem.ls();

		try {
			fileSystem.addContentToFile("/a/b/c/abc.txt", "Hello");
		} catch (Exception e) {
			System.out.println(e);
		}

		File file = fileSystem.cat("/a/b/c/abc.txt");
		System.out.println("After adding file..");
		fileSystem.ls();
		fileSystem.addContentToFile("/a/b/c/abc.txt", "Hello");
		fileSystem.addContentToFile("/a/b/c/abc.txt", " World");
		Assertions.assertTrue(file.getContent().equals("Hello World"));
	}

}
