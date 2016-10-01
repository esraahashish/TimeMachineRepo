package com.mvc.timemachine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.HashTag;
import com.mvc.timemachine.models.Information;


@Repository
public interface InformationRepo extends CrudRepository<Information, Integer> {
	
//	@Query("Select i from Information i where i.date >= :startDate AND i.id >= :startId ORDER BY i.date ASC")
//	public List<Information> getEraNewsFeed(@Param("startDate") LocalDateTime date , @Param("startId") Long startId);
	
	@Query("select i from Information i where i.era.id = :eraId and :hashtag in i.informationHashTags order by i.date ASC")
	public List<Information> getEraHashtaginfo(@Param("eraId") Long eraId , @Param("hashtag") HashTag hashtag);
}

