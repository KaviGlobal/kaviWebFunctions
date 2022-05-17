package com.kavi.web.repo;

import java.util.List;
import static com.kavi.web.utils.QueryConstants.GET_ITEM_BY_TAG_ID;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kavi.web.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

	Optional<Item> findById(Integer id);

	List<Item> findByTitle(String title);

	@Query(value = GET_ITEM_BY_TAG_ID , nativeQuery = true)
	List<Item> findByTagId(@Param("tagId") String tagId);

}
