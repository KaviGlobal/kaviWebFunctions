package com.kavi.web.repo;

import org.springframework.data.repository.CrudRepository;

import com.kavi.web.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

}
