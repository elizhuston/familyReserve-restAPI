package com.family.familyReserve.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RecipeRepository extends JpaRepository<Recipe , Integer> {
	@Query("SELECT r from Recipe r LEFT JOIN r.family as f where f.id=:id")
	public List<Recipe> findFamilyRecipes(@Param("id") int id);
	
}



