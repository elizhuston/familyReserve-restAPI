package com.family.familyReserve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRelationshipRepo extends JpaRepository<PersonRelationship, Integer>{

@Query("SELECT p from Person as p LEFT JOIN p.relatives as relatives WHERE UPPER(firstName) like UPPER(CONCAT('%',:firstName, '%'))")
public Person findRelativeByName(@Param("firstName") String firstName);

@Query("SELECT p from Person as p LEFT JOIN p.relatives as relatives WHERE p.id=:id")
public <List>Person findRelationship(@Param("id") long id);

	}

