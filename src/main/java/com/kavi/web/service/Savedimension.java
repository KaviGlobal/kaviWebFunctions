package com.kavi.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kavi.web.entity.Dimension;
import com.kavi.web.model.DimensionDto;
import com.kavi.web.model.ResponseStatus;
import com.kavi.web.repo.DimensionRepository;

/**
 * This service is used to create or update a dimension in the dimension table based on
 * the ID provided
 * 
 * @author Ashwini
 *
 */
@Component
public class Savedimension implements Function<DimensionDto, ResponseStatus> {

	@Autowired
	private DimensionRepository itemRepo;

	public ResponseStatus apply(DimensionDto data) {
		if (data.getId() != null) {
			// update item
			this.updateItem(data);
		} 
		return new ResponseStatus(1, "Success");
	}

	/*private Dimension createItem(DimensionDto itemDto) {
		Dimension item = new Dimension(itemDto);
		Dimension result = itemRepo.save(item);

		// create item tag mapping
		itemRepo.saveAll(this.getItemTagMappings(result.getId(), itemDto.getId()));

		return result;

	}*/

	private Dimension updateItem(DimensionDto itemDto) {


			Dimension item = new Dimension(itemDto);
			item.setId(itemDto.getId());
			itemRepo.save(item);

			// TODO - should we update/create the tags or not . Should we update the
			// existing tags or create new tags ?
		
			return item;
	}

}
