package com.kavi.web.function;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.MultipartStream;
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
import com.microsoft.azure.storage.file.FileOutputStream;

public class UploadItemFunction extends FunctionInvoker<ItemFileDto, Item> {

	@FunctionName("uploadItem")
	public HttpResponseMessage execute(@HttpTrigger(name = "req", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) throws IOException {

		context.getLogger().info("Java HTTP file upload started with headers " + request.getHeaders());
		Map<String, String> queryParam = request.getQueryParameters();
		final String id = queryParam.get("id");
		final String containerName = "item";
		 String filename = null;

		// Code will be refactored later
		String connectionString = "DefaultEndpointsProtocol=https;"
				+ "AccountName=kaviwebstorage;AccountKey=P2hplMoC92WDVUGQnyW3ZxUgEDQ7riI0voWnPQqYeOlsigwWw/9F1QTTA4kKFoiPd3cp9kJLHkH0uAL35n/IZA==;EndpointSuffix=core.windows.net";

		try {

			String contentType = request.getHeaders().get("content-type"); // Get content-type header
			// here the "content-type" must be lower-case
			String body = request.getBody().get(); // Get request body
			
			InputStream in = new ByteArrayInputStream(body.getBytes()); // Convert body to an input stream
			String boundary = contentType.split(";")[1].split("=")[1]; // Get boundary from content-type header
			int bufSize = 1024;
			// Using MultipartStream to parse body input stream
			MultipartStream multipartStream = new MultipartStream(in, boundary.getBytes(), bufSize, null);
			boolean nextPart = multipartStream.skipPreamble();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (nextPart) {
				String header = multipartStream.readHeaders();
				 int start = header.indexOf("filename=")+"filename=".length()+1;
	                int end = header.indexOf("\r\n")-1;
	              filename  = header.substring(start, end);
				multipartStream.readBodyData(baos);
				nextPart = multipartStream.readBoundary();
			}

			BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString)
					.buildClient();
			BlobContainerClient containerClient = null;

			containerClient = blobServiceClient.getBlobContainerClient(containerName);
			if (!containerClient.exists())
				blobServiceClient.createBlobContainer(containerName);

			// create file inside the folder having the ID name
			BlobClient blob = containerClient.getBlobClient(id + "/"+filename);
			blob.upload(new ByteArrayInputStream(baos.toByteArray()), baos.size(), true);

		} catch (Exception ex) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(ex.getMessage()).build();
		}

		return request.createResponseBuilder(HttpStatus.OK).body("File uploaded successfully ").build();

	}

}