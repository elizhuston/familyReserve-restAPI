package com.family.familyReserve.domain;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface RelationTypeRepository extends JpaRepository<RelationType, Integer>{

	@Query("SELECT t from RelationType t WHERE UPPER(description) = UPPER(:description)")
	public Person findRelationTypeByDesc(@Param("description") String description);

	@Query("SELECT t from RelationType t")
	public List<RelationType> findAllRelationTypes();

}

