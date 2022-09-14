package com.kavi.web.function;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.web.multipart.MultipartFile;

import com.kavi.web.model.ResponseStatus;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class UploadImageFunction extends FunctionInvoker<String, ResponseStatus>{

	@FunctionName("uploadImage")
	public HttpResponseMessage execute(@HttpTrigger(name = "req", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) throws IOException {

		//Map<String, String> query = request.getQueryParameters();
		//String id = query.get("id");
		context.getLogger().info("Java HTTP image upload started with headers " + request.getHeaders());
		String contentType = request.getHeaders().get("content-type");
		String body = request.getBody().get(); // Get request body

		/*String boundary = contentType.split(";")[1].split("=")[1]; // Get boundary from content-type header
		int bufSize = 1024;*/
		// Using MultipartStream to parse body input stream
		//Multipart multipartStream = new MultipartStream(in, boundary.getBytes(), bufSize, null);

		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(body, context))
				.header("Content-Type", "application/json").build();
	}

}