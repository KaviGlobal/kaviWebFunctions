package com.kavi.web.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;

import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.kavi.web.entity.Media;
import com.kavi.web.model.MediaDto;
import com.kavi.web.model.ResponseStatus;
import com.kavi.web.repo.MediaRepository;

/**
 * This service is used to upload an image into the azure storage and return
 * back the inserted image meta data from the media table
 * 
 * @author Nilesh.Dhiman
 *
 */
@Component
public class UploadImage implements Function<String, ResponseStatus> {

	private static final String IMAGE_TYPE_ID = "image";
	private static final String imageDesc = "Image";
	@Autowired
	private MediaRepository mediaRepo;
	@Autowired
	private ImageAnalysisService imageService;

	public ResponseStatus apply(String encodedImage) {

		final String containerName = "images2";
		String filename = "full.png";

		// Code will be refactored later
		String connectionString = "DefaultEndpointsProtocol=https;"
				+ "AccountName=kaviwebstorage;AccountKey=P2hplMoC92WDVUGQnyW3ZxUgEDQ7riI0voWnPQqYeOlsigwWw/9F1QTTA4kKFoiPd3cp9kJLHkH0uAL35n/IZA==;EndpointSuffix=core.windows.net";

		// Persist the meta data into the database
		MediaDto media = new MediaDto(null, "full.png", imageDesc, IMAGE_TYPE_ID);
		Media mediaEntity = new Media(media);
		Media result = mediaRepo.save(mediaEntity);

		// Image upload
		byte[] decodedData = Base64.getDecoder().decode(encodedImage);

		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString)
				.buildClient();
		BlobContainerClient containerClient = null;

		containerClient = blobServiceClient.getBlobContainerClient(containerName);
		if (!containerClient.exists())
			blobServiceClient.createBlobContainer(containerName);

		// create file inside the folder having the ID name
		BlobClient blobClient = containerClient.getBlobClient(result.getId() + "/" + filename);
		blobClient.upload(new ByteArrayInputStream(decodedData), decodedData.length, true);

		// Generate Thumbnail

		try {
			HttpEntity thumbnailEntity = this.imageService
					.generateThumbnail(URLDecoder.decode(blobClient.getBlobUrl(), StandardCharsets.UTF_8), 466, 310);
			BlobClient thumbnailBlobClient = containerClient.getBlobClient(result.getId() + "/" + "thumbnail.png");
			thumbnailBlobClient.upload(thumbnailEntity.getContent(), thumbnailEntity.getContentLength(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseStatus(result.getId(), "Success");
	}

}