package com.family.familyReserve.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "stories", path = "stories")
public interface StoryRepository extends  PagingAndSortingRepository<Story, Integer>{
	
}


