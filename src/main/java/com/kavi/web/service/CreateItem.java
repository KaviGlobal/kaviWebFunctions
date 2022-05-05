package com.kavi.web.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kavi.web.entity.Item;
import com.kavi.web.model.ItemDto;
import com.kavi.web.model.ResponseStatus;
import com.kavi.web.repo.ItemRepository;

import reactor.core.publisher.Mono;

@Component
public class CreateItem implements Function<ItemDto, ResponseStatus> {

	@Autowired
	private ItemRepository itemRepo;

	public ResponseStatus apply(ItemDto data) {

		Item item = new Item(data);
		Item result = itemRepo.save(item);
		
		return new ResponseStatus(result.toString());
	}
}