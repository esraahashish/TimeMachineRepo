package com.mvc.timemachine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.Sound;

@Repository
public interface SoundRepo extends CrudRepository<Sound, Integer> {

}
