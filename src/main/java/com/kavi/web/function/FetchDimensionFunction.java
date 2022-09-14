package com.kavi.web.function;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import com.kavi.web.model.ItemTagDto;
import com.kavi.web.model.ResponseStatus;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class FetchDimensionFunction extends FunctionInvoker<Integer, ResponseStatus> {

	//@FunctionName("getDimension")
	public HttpResponseMessage execute(@HttpTrigger(name = "req", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Integer>> request,
			final ExecutionContext context) {

		 // Parse query parameter
        final Integer itemId = Integer.parseInt(request.getQueryParameters().get("id"));
        
		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(itemId, context))
				.header("Content-Type", "application/json").build();
	}

}