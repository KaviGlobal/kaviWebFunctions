package com.kavi.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kavi.web.entity.Item;
import com.kavi.web.model.ItemDto;
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
public class GetItemsByTypeTag implements Function<ItemTypeTagRequest, List<ItemDto>> {

	@Autowired
	private ItemRepository itemRepo;

	public List<ItemDto> apply(ItemTypeTagRequest request) {

		List<ItemDto> result = new ArrayList();

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