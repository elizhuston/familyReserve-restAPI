package com.family.familyReserve;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRepository  extends JpaRepository<Family, Integer>{

	@Query("SELECT f from Family f WHERE UPPER(name) = UPPER(:name)")
	public Family findFamilyByName(@Param("name") String name);
		
	@Query("SELECT relatives from Family as f LEFT JOIN f.members as members LEFT JOIN members.relative as relatives"
			+ " WHERE f.id = :id")
	public List<Person> findFamilyMembers(@Param("id") int id);
	
}
