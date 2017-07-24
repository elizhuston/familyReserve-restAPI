package com.family.familyReserve.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FamilyRoleRepository extends JpaRepository<FamilyRole, Integer> {

	@Query("Select fr from FamilyRole as fr LEFT JOIN fr.family as family LEFT JOIN fr.person "
			+ " WHERE family.id=:id and fr.role='ADMIN'")
	public List<FamilyRole> findAdminsForFamily(@Param("id") int id);

}

