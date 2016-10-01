package com.mvc.timemachine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mvc.timemachine.models.Era;
import com.mvc.timemachine.models.HashTag;

@Repository
public interface HashTagRepo extends JpaRepository<HashTag, Integer> {
	
	@Query("Select h from HashTag h ORDER BY h.frequency ASC")
	public List<HashTag> getHashTags();
	
	@Query("Select h from HashTag h where h.name = :hashtagName")
	public HashTag getHashtagByName(@Param("hashtagName") String hashtagName);
	
	@Query("Select h from HashTag h where h.name = :hashtagName and :era in h.hashTagEras")
	public HashTag getHashtagByNameandEra(@Param("hashtagName") String hashtagName , @Param("era") Era era);
}
