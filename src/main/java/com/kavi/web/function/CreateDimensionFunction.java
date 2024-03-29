package com.kavi.web.function;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import com.kavi.web.entity.Dimension;
import com.kavi.web.model.DimensionDto;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class CreateDimensionFunction extends FunctionInvoker<DimensionDto, Dimension> {

	//@FunctionName("saveDimension")
	public HttpResponseMessage execute(@HttpTrigger(name = "req", methods = {
        HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<DimensionDto>> request,
        final ExecutionContext context) {

    context.getLogger().info("Request body is: " + request.getBody().orElse(null));

    DimensionDto dimension = request.getBody().get();

    // handleRequest(item,context);

    return request.createResponseBuilder(HttpStatus.OK).body(handleRequest(dimension, context))
            .header("Content-Type", "application/json").build();
	}
	


}