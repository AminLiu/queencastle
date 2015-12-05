package com.queencastle.service.interf;

import java.io.File;

public interface ResourceUploadService {

	String uploadFile(File file, String randKey);

	String uploadBytes(byte[] data, String randKey);
}
