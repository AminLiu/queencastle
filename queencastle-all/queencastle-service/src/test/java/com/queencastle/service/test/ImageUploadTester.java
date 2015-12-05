package com.queencastle.service.test;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

public class ImageUploadTester extends BaseTest {

	@Test
	@Ignore
	public void testUpload() {
		resourceUploadService.uploadFile(new File("d:/hello.jpg"), "");
	}
}
