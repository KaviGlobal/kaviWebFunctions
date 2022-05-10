package com.kavi.web.utils;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

public class StorageUtility {

	
	private String uploadBlobStorage(MultipartFile file) throws IOException {
		
		String connectionString = "DefaultEndpointsProtocol=https;"
				+ "AccountName=kaviwebstorage;AccountKey=P2hplMoC92WDVUGQnyW3ZxUgEDQ7riI0voWnPQqYeOlsigwWw/9F1QTTA4kKFoiPd3cp9kJLHkH0uAL35n/IZA==;EndpointSuffix=core.windows.net";


		BlobContainerClient container = new BlobContainerClientBuilder()
				.connectionString(connectionString)
				.containerName("testNilesh")
				.buildClient();
		
		BlobClient blob = container.getBlobClient(file.getOriginalFilename());
		blob.upload(file.getInputStream(), file.getSize(), true);
		
		return "OK";
	}
}
