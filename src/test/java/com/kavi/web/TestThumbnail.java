package com.kavi.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;

import rx.Observable;
import rx.observables.StringObservable;
public class TestThumbnail {
	
	private static String COMPUTER_VISION_SUBSCRIPTION_KEY = "0180fee75e6a4d8e89b5fad55d80ca8c";
	private static String COMPUTER_VISION_ENDPOINT = "https://kaviwebsitecomputervision.cognitiveservices.azure.com";
	private static String connectionString = "DefaultEndpointsProtocol=https;"
			+ "AccountName=kaviwebstorage;AccountKey=P2hplMoC92WDVUGQnyW3ZxUgEDQ7riI0voWnPQqYeOlsigwWw/9F1QTTA4kKFoiPd3cp9kJLHkH0uAL35n/IZA==;EndpointSuffix=core.windows.net";


	private static ComputerVisionClient authenticate(String subscriptionKey, String endpoint) {
		return ComputerVisionManager.authenticate(subscriptionKey).withEndpoint(endpoint);
	}


	public static void main(String[] args) {
		String containerName = "images2";
		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString)
				.buildClient();
		BlobContainerClient containerClient = null;

		containerClient = blobServiceClient.getBlobContainerClient(containerName);
		if (!containerClient.exists())
			blobServiceClient.createBlobContainer(containerName);
		
		BlobClient thumbnailBlobClient = containerClient.getBlobClient("9" + "/" + "thumbnail.png");
		
		ComputerVisionClient client = authenticate(COMPUTER_VISION_SUBSCRIPTION_KEY, COMPUTER_VISION_ENDPOINT);

		//InputStream ipStrm = client.computerVision().generateThumbnail(360, 455, "https://kaviwebstorage.blob.core.windows.net/images2/9/full.png", null);

		Observable<InputStream>  is =   client.computerVision().generateThumbnailAsync(360, 455, "https://kaviwebstorage.blob.core.windows.net/images2/9/full.png", null);
			
		
		
		//thumbnailBlobClient.upload(   , 0);
		
		/*
		 * try {
		 * 
		 * 
		 * 
		 * String text = new String(ipStrm.readAllBytes(), StandardCharsets.UTF_8);
		 * thumbnailBlobClient.upload(ipStrm, text.length(), true);
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

}
