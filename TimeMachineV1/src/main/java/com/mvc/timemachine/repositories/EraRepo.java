package com.mvc.timemachine.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.Era;


@Repository
public interface EraRepo extends CrudRepository<Era, Long> {
//	@Query("select e from era")
//	public List<Era> getEras();
}
