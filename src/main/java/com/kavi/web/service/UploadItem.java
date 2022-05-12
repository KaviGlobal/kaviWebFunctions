package com.kavi.web.service;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.kavi.web.model.ItemFileDto;
import com.kavi.web.model.ResponseStatus;

/**
 * This service is used to upload the item file for a given id. The id will be
 * used as a container name
 * 
 * @author Nilesh.Dhiman
 *
 */
@Component
public class UploadItem implements Function<ItemFileDto, ResponseStatus> {

	public ResponseStatus apply(ItemFileDto data) {

		System.out.println( " Item id is > "+data.getId());
		System.out.println("File name is > "+ data.getFile().getOriginalFilename());
		System.out.println("File size > "+data.getFile().getSize());
		return new ResponseStatus("Test file upload", "Success");
	}

}