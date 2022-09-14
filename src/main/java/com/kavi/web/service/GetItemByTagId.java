package com.kavi.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kavi.web.entity.Item;
import com.kavi.web.model.ItemTagDto;
import com.kavi.web.repo.ItemRepository;

/**
 * This service is used to retrieve list of Items for a given tagId the ID
 * provided
 * 
 * @author Nilesh.Dhiman
 *
 */
@Component
public class GetItemByTagId implements Function<String, List<ItemTagDto>> {

	@Autowired
	private ItemRepository itemRepo;

	@Override
	public List<ItemTagDto> apply(String tagId) {

		List<ItemTagDto> result = new ArrayList();

		List<Item> items = itemRepo.findByTagId(tagId);
		for (Item item : items) {

			result.add(new ItemTagDto(item.getId(), item.getTitle(), item.getTypeId(), null, item.getCreatedBy()));
		}

		return result;
	}

}