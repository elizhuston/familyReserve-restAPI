package com.family.familyReserve.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRepository  extends JpaRepository<Family, Integer>{

	@Query("SELECT f from Family f WHERE UPPER(name) = UPPER(:name)")
	public Family findFamilyByName(@Param("name") String name);
		
	@Query("SELECT f.members from Family as f WHERE f.id = :id")
	public List<Person> findFamilyMembers(@Param("id") int id);

	@Query("SELECT f from Family f")
	public List<Family> findAllFamilies();

	@Query("SELECT f from Person p LEFT JOIN p.families as f where p.id=:id")
	public List<Family> findFamiliesForPerson(@Param("id") int id);
	
	
	@Query("SELECT f from Family as f where f.id=:id")
	public Family findFamilyById(@Param("id") int id);
}
