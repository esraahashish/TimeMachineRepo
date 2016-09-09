package com.mvc.timemachine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.Era;

@Repository
public interface EraRepo extends CrudRepository<Era, Integer> {

}
