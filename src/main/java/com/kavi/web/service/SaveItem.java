package com.kavi.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kavi.web.entity.Item;
import com.kavi.web.entity.ItemTag;
import com.kavi.web.model.ItemDto;
import com.kavi.web.model.ItemTagDto;
import com.kavi.web.model.ResponseStatus;
import com.kavi.web.repo.ItemRepository;
import com.kavi.web.repo.ItemTagRepository;

/**
 * This service is used to create or update an item in the Item table based on
 * the ID provided
 * 
 * @author Nilesh.Dhiman
 *
 */
@Component
public class SaveItem implements Function<ItemDto, ResponseStatus> {

	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private ItemTagRepository itemTagRepo;

	public ResponseStatus apply(ItemDto data) {
		Item result = null;
		if (data.getId() != null) {
			// update item
			result = this.updateItem(data);
		} else {
			// create item
			result = this.createItem(data);
		}

		return new ResponseStatus(result.getId(), "Success");
	}

	private Item createItem(ItemDto itemDto) {
		Item item = new Item(itemDto);
		Item result = itemRepo.save(item);

		// create item tag mapping
		//itemTagRepo.saveAll(this.getItemTagMappings(result.getId(), itemDto.getTagIds()));

		return result;

	}

	private Item updateItem(ItemDto itemDto) {

		Item result = null;
		if (itemRepo.findById(itemDto.getId()).isPresent()) {
			Item item = new Item(itemDto);
			item.setId(itemDto.getId());
			result = itemRepo.save(item);

			// TODO - should we update/create the tags or not . Should we update the
			// existing tags or create new tags ?
		}
		return result;
	}

	private List<ItemTag> getItemTagMappings(Integer itemId, List<String> tagIds) {
		List<ItemTag> itemTags = new ArrayList<>();
		for (String tagId : tagIds) {
			// TODO :- Score logic TBD
			itemTags.add(new ItemTag(itemId, tagId, null));
		}
		return itemTags;
	}
}