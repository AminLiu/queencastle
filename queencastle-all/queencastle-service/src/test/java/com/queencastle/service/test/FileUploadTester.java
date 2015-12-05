package com.queencastle.service.test;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

public class FileUploadTester extends BaseTest {

    @Test
    @Ignore
    public void testUpload() {
        resourceUploadService.uploadFile(new File("d:/QQ图片20150413193844.jpg"),
                "20150413193844.jpg");
    }
}
