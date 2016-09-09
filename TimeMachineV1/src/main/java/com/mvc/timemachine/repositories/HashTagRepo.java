package com.mvc.timemachine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.HashTag;

@Repository
public interface HashTagRepo extends CrudRepository<HashTag, Integer> {

}
