package com.mvc.timemachine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.Information;

@Repository
public interface InformationRepo extends CrudRepository<Information, Integer> {

}
