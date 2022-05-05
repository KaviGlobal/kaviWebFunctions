package com.kavi.web.function;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import com.kavi.web.model.ItemDto;
import com.kavi.web.model.ResponseStatus;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class CreateItemFunction extends FunctionInvoker<ItemDto, ResponseStatus> {

	@FunctionName("createItem")
	public HttpResponseMessage execute(@HttpTrigger(name = "req", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<ItemDto>> request,
			final ExecutionContext context) {

		context.getLogger().info("Request body is: " + request.getBody().orElse(null));

		ItemDto item = request.getBody().get();

		// handleRequest(item,context);

		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(item, context))
				.header("Content-Type", "application/json").build();
	}

}