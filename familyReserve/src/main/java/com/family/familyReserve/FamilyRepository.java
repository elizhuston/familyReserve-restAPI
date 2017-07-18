package com.family.familyReserve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRepository  extends JpaRepository<Family, Integer>{

	@Query("SELECT f from Family f WHERE UPPER(name) = UPPER(:name)")
	public Family findFamilyByName(@Param("name") String name);

	
}
