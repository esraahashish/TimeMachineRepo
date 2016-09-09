package com.mvc.timemachine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.Photo;

@Repository
public interface PhotoRepo extends CrudRepository<Photo, Integer> {

}
