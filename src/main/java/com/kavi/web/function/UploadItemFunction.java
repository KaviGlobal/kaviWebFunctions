package com.kavi.web.function;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.kavi.web.entity.Item;
import com.kavi.web.model.ItemFileDto;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class UploadItemFunction extends FunctionInvoker<ItemFileDto, Item> {

	@FunctionName("uploadItem")
	public HttpResponseMessage execute(@HttpTrigger(name = "req", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<byte[]>> request,
			final ExecutionContext context) throws IOException {

		// context.getLogger().info("Request body is: " +
		// request.getBody().orElse(null));
		// Flux<ByteBuffer> body = request.getBody();
		context.getLogger().info("Java HTTP file upload started with headers " + request.getHeaders());
		// byte[] bs = request.getBody().get();
		Map<String, String> queryParam = request.getQueryParameters();
		String containerID = queryParam.get("id");
		String containerName = "kaviweb-" + containerID;

		// Code will be refactored later
		String connectionString = "DefaultEndpointsProtocol=https;"
				+ "AccountName=kaviwebstorage;AccountKey=P2hplMoC92WDVUGQnyW3ZxUgEDQ7riI0voWnPQqYeOlsigwWw/9F1QTTA4kKFoiPd3cp9kJLHkH0uAL35n/IZA==;EndpointSuffix=core.windows.net";

		try {

			long bodyLength = request.getBody().get().length;
			String contentLength = request.getHeaders().get("content-length");
			InputStream inputStream = new ByteArrayInputStream(request.getBody().get());

			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString)
					.buildClient();
			BlobContainerClient containerClient = blobServiceClient.createBlobContainer(containerName);

			BlobClient blob = containerClient.getBlobClient("full.md");
			blob.upload(inputStream, bodyLength, true);
		} catch (Exception ex) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(ex.getMessage()).build();
		}

		return request.createResponseBuilder(HttpStatus.OK)
				.body("File uploaded successfully in container " + containerName).build();

	}

}