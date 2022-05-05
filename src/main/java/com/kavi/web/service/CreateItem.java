package com.kavi.web.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kavi.web.entity.Item;
import com.kavi.web.entity.ItemTag;
import com.kavi.web.model.ItemDto;
import com.kavi.web.model.ResponseStatus;
import com.kavi.web.repo.ItemRepository;
import com.kavi.web.repo.ItemTagRepository;

@Component
public class CreateItem implements Function<ItemDto, ResponseStatus> {

	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private ItemTagRepository itemTagRepo;

	public ResponseStatus apply(ItemDto data) {

		Item item = new Item(data);
		Item result = itemRepo.save(item);

		// create item tag mapping
		ItemTag itemTag = itemTagRepo.save(new ItemTag(result.getId(), data.getTagId(), null));

		return new ResponseStatus(result.toString());
	}
}