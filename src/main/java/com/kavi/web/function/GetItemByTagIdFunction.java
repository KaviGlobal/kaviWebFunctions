package com.kavi.web.function;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import com.kavi.web.model.ItemTagDto;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class GetItemByTagIdFunction extends FunctionInvoker<String, List<ItemTagDto>> {

	@FunctionName("getItemByTagId")
	public HttpResponseMessage execute(@HttpTrigger(name = "req", methods = {
			HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
			final ExecutionContext context) {

		Map<String, String> queryParams = request.getQueryParameters();
		String tagId = queryParams.get("tagId");

		return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(tagId, context))
				.header("Content-Type", "application/json").build();
	}

}