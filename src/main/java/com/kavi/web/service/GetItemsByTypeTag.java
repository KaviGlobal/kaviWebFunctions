package com.kavi.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kavi.web.entity.Item;
import com.kavi.web.model.ItemTagDto;
import com.kavi.web.model.ItemTypeTagRequest;
import com.kavi.web.repo.ItemRepository;

/**
 * This service is used to retrieve list of Items for a given tagId the ID
 * provided
 * 
 * @author Nilesh.Dhiman
 *
 */
@Component
public class GetItemsByTypeTag implements Function<ItemTypeTagRequest, List<ItemTagDto>> {

	@Autowired
	private ItemRepository itemRepo;

	public List<ItemTagDto> apply(ItemTypeTagRequest request) {

		List<ItemTagDto> result = new ArrayList();

		List<String> tagIds = request.getTagIds();
		List<String> itemTypes = request.getItemTypes();
		
		/*
		 * List<Item> items = new ArrayList<>() if(!itemTypes.isEmpty()) {
		 * 
		 * List<Item> items = itemRepo.findByItemTypes(itemTypes); }
		 */

		return result;
	}

}