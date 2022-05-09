package com.kavi.web.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kavi.web.entity.Item;
import com.kavi.web.repo.ItemRepository;

/**
 * This service is used to fetch an item from the Item table based on
 * the ID provided
 * 
 * @author Nilesh.Dhiman
 *
 */
@Component
public class GetItem implements Function<Integer, Item> {

	@Autowired
	private ItemRepository itemRepo;

	public Item apply(Integer itemId) {
		Item result = null;

		Optional<Item> item = itemRepo.findById(itemId);
		if (item.isPresent())
			result = item.get();

		return result;
	}

}