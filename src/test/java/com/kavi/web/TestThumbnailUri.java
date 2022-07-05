package com.kavi.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;

import rx.Observable;
import rx.observables.StringObservable;
public class TestThumbnailUri {
	
	private static String COMPUTER_VISION_SUBSCRIPTION_KEY = "0180fee75e6a4d8e89b5fad55d80ca8c";
	private static String COMPUTER_VISION_ENDPOINT = "https://kaviwebsitecomputervision.cognitiveservices.azure.com/vision/v3.1/generateThumbnail";
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

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		try {
			URIBuilder uriBuilder = new URIBuilder(COMPUTER_VISION_ENDPOINT);
			uriBuilder.setParameter("width", "466");
			uriBuilder.setParameter("height", "310");
			//uriBuilder.setParameter("smartCropping", null);
			
			URI uri = uriBuilder.build();
			HttpPost request = new HttpPost(uri);
			
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", COMPUTER_VISION_SUBSCRIPTION_KEY);
			
			String imageUrl = "https://kaviwebstorage.blob.core.windows.net/images2/9/full.png";
			StringEntity requestEntity = new StringEntity("{\"url\":\"" + imageUrl + "\"}");
			request.setEntity(requestEntity);
			
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			
			
			
			System.out.println("Status :"+response.getStatusLine().getStatusCode());
			
			thumbnailBlobClient.upload(entity.getContent(), entity.getContentLength(), true);
			httpClient.close();
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
