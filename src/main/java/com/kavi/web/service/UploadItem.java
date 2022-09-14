package com.kavi.web.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kavi.web.entity.Item;
import com.kavi.web.entity.ItemTag;
import com.kavi.web.model.ItemTagDto;
import com.kavi.web.model.ItemDto;
import com.kavi.web.model.ItemFileDto;
import com.kavi.web.model.ResponseStatus;
import com.kavi.web.repo.ItemRepository;

import io.netty.handler.codec.json.JsonObjectDecoder;

/**
 * This service is used to upload the item file for a given id. The id will be
 * used as a container name
 * 
 * @author Nilesh.Dhiman
 *
 */
@Component
public class UploadItem implements Function<String, ItemDto> {


	@Autowired
	private ItemRepository itemRepo;


	public ItemDto apply(String id) throws NullPointerException{

		ItemDto result = null;

		Integer itemId = Integer.valueOf(id);

		Optional<Item> item = itemRepo.findById(itemId);

		if (item.isPresent()) {
			Item itemData = item.get();
			result = new ItemDto(itemData.getId(), itemData.getTitle(), itemData.getTypeId(), itemData.getCreatedBy());
		}

		return result;
	}

	


}