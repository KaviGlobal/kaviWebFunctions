package com.kavi.web.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kavi.web.entity.Media;

@Repository
public interface MediaRepository extends CrudRepository<Media, Integer> {


}
