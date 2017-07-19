package com.family.familyReserve;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRelationshipRepo extends JpaRepository<PersonRelationship, Integer> {

	@Query("SELECT p from Person as p LEFT JOIN p.relatives as relatives WHERE UPPER(firstName) like UPPER(CONCAT('%',:firstName, '%'))")
	public Person findRelativeByName(@Param("firstName") String firstName);

	@Query("SELECT p from Person as p LEFT JOIN p.relatives as relatives WHERE p.id=:id")
	public <List> Person findRelationship(@Param("id") int id);

	@Query("SELECT relative from PersonRelationship as pr WHERE person_id=:id")
	public List<Person> findRelativesForPerson(@Param("id") int id);
	
//	@Query("Select m from Movie as m LEFT JOIN m.authors as authors where UPPER(authors.name) like UPPER(CONCAT('%',:name, '%'))")
//	public List<Movie> findByAuthorLike(@Param("name") String name);
}
