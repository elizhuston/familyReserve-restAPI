package com.family.familyReserve.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "posts", path = "posts")
public interface PostRepository extends  PagingAndSortingRepository<Post, Integer>{

	@Query("SELECT p from Post p LEFT JOIN p.family as f where f.id=:id order by p.id DESC, p.postDate DESC ")
	public List<Post> findFamilyPosts(@Param("id") int id);
	

}
