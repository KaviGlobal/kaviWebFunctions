package com.kavi.web.repo;

import java.util.List;
import static com.kavi.web.utils.QueryConstants.GET_ITEMS_BY_TAG_ID;
import static com.kavi.web.utils.QueryConstants.GET_ITEMS_BY_ITEM_TYPE;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kavi.web.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

	public Optional<Item> findById(Integer id);

	public List<Item> findByTitle(String title);

	@Query(value = GET_ITEMS_BY_TAG_ID , nativeQuery = true)
	public List<Item> findByTagId(@Param("tagId") String tagId);
	
	@Query(value = GET_ITEMS_BY_ITEM_TYPE, nativeQuery = true )
	public List<Item> findByItemTypes(@Param("itemTypes") List<String> itemTypes);
	
	

}
