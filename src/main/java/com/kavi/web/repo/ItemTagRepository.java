package com.kavi.web.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kavi.web.entity.ItemTag;

@Repository
public interface ItemTagRepository extends CrudRepository<ItemTag, Integer> {

}
