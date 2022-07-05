package com.kavi.web.function;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import com.kavi.web.model.ResponseStatus;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class UploadImageFunction extends FunctionInvoker<String, ResponseStatus> {

	@FunctionName("uploadImage")
	public HttpResponseMessage execute(@HttpTrigger(name = "req", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) {

		context.getLogger().info("Java HTTP image upload started with headers " + request.getHeaders());
		String body = request.getBody().get(); // Get request body

		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(body, context))
				.header("Content-Type", "application/json").build();
	}

}