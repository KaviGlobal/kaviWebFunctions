package com.kavi.web.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.kavi.web.entity.Media;
import com.kavi.web.model.MediaDto;
import com.kavi.web.model.ResponseStatus;
import com.kavi.web.repo.MediaRepository;
import com.kavi.web.service.SaveItem;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobOutputStream;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

/**
 * This service is used to upload an image into the azure storage and return
 * back the inserted image meta data from the media table
 * 
 * @author Nilesh.Dhiman
 *
 */
@Component
public class UploadImage implements Function<String , ResponseStatus> {

	private static final String IMAGE_TYPE_ID = "image";
	private static final String imageDesc = "Image";
	
	//private static String COMPUTER_VISION_SUBSCRIPTION_KEY = "0180fee75e6a4d8e89b5fad55d80ca8c";
	//private static String COMPUTER_VISION_ENDPOINT = "https://kaviwebsitecomputervision.cognitiveservices.azure.com/vision/v3.1/generateThumbnail";


	@Autowired
	private MediaRepository mediaRepo;
	@Autowired
	private ImageAnalysisService imageService;

	public ResponseStatus apply(String encodedImage) {

		final String containerName = "kaviwebsiteobjects";
		String filename = "full.jpg";

		// Code will be refactored later
		String connectionString = "DefaultEndpointsProtocol=https;"
				+ "AccountName=kaviwebstorage;AccountKey=P2hplMoC92WDVUGQnyW3ZxUgEDQ7riI0voWnPQqYeOlsigwWw/9F1QTTA4kKFoiPd3cp9kJLHkH0uAL35n/IZA==;EndpointSuffix=core.windows.net";

		// Persist the meta data into the database
	
		Optional<Media> result1 = mediaRepo.findById(1);
		Media result = result1.get();

		// Image upload
		//MultipartFile file = new MultipartFile();
		//byte[] decodedData = Base64.getDecoder().decode(encodedImage);


		try {

			/*CloudStorageAccount storageAccount = CloudStorageAccount.parse(connectionString);

			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			CloudBlobContainer container = blobClient.getContainerReference(containerName);
		  
			byte[] bytes = IOUtils.toByteArray(encodedImage);
		    CloudBlockBlob blob = container.getBlockBlobReference("image/"+result.getId() + "/" +filename);
		    BlobOutputStream blobOutputStream = blob.openOutputStream();
			ByteArrayInputStream inputStream = new ByteArrayInputStream(encodedImage.getBytes("UTF-8")); 

			int next = inputStream.read();
			while (next != -1) {
				  blobOutputStream.write(next);
				  next = inputStream.read();
			}
			blobOutputStream.close();*/
			byte[] decodedData = Base64.getDecoder().decode(encodedImage);


			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString)
					.buildClient();
			BlobContainerClient containerClient = null;
	
			containerClient = blobServiceClient.getBlobContainerClient(containerName);
			if (!containerClient.exists())
				blobServiceClient.createBlobContainer(containerName);
	
			// create file inside the folder having the ID name
			BlobClient blobClient = containerClient.getBlobClient("image/"+result.getId() + "/" + filename);
			blobClient.upload(new ByteArrayInputStream(decodedData), decodedData.length , true);
	


			String metadata = result.toString();

			BlobClient metadataBlob = containerClient.getBlobClient("image/"+result.getId() + "/metadata.json");
			metadataBlob.upload(new ByteArrayInputStream(metadata.getBytes()),metadata.length(), true);
	
	
			// Generate Thumbnail

			HttpEntity thumbnailEntity = this.imageService.generateThumbnail(URLDecoder.decode(blobClient.getBlobUrl(), StandardCharsets.UTF_8), 466, 310);
			BlobClient thumbnailBlobClient = containerClient.getBlobClient("image/"+result.getId() + "/" + "thumbnail.jpg");
			thumbnailBlobClient.upload(thumbnailEntity.getContent(), thumbnailEntity.getContentLength(), true);



		/*Map<String, String> data = new HashMap<String, String>();
		data.put("id", String.valueOf(mediaEntity.getId()));
		data.put("name", mediaEntity.getName());
		data.put("description", mediaEntity.getDescription());
		data.put("typeId", mediaEntity.getTypeId());*/

		} catch (IOException e) {
			e.printStackTrace();
		} 

		return new ResponseStatus(result.getId(), "Success");
	}

	/*
	 * private ComputerVisionClient authenticate(String subscriptionKey, String
	 * endpoint) { return
	 * ComputerVisionManager.authenticate(subscriptionKey).withEndpoint(endpoint); }
	 */

	/*public HttpEntity generateThumbnail(String url, Integer width, Integer height) {

		/*
		 * 
		 * 
		 * InputStream ipStrm = client.computerVision().generateThumbnail(width, height,
		 * url, null);
		 */

		/*CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
			URIBuilder uriBuilder = new URIBuilder(COMPUTER_VISION_ENDPOINT);
			uriBuilder.setParameter("width", String.valueOf(width));
			uriBuilder.setParameter("height", String.valueOf(height));
			// uriBuilder.setParameter("smartCropping", null);

			URI uri = uriBuilder.build();
			HttpPost request = new HttpPost(uri);

			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", COMPUTER_VISION_SUBSCRIPTION_KEY);

			StringEntity requestEntity = new StringEntity("{\"url\":\"" + url + "\"}");
			request.setEntity(requestEntity);

			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();

			if (response.getStatusLine().getStatusCode() == 200) {
				return entity;
			}

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

		return null;

	}*/

}