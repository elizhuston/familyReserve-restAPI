package com.family.familyReserve.domain;

//
//import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends  PagingAndSortingRepository<Person, Long>{
	
	@Query("SELECT p from Person p WHERE UPPER(userName) = UPPER(:userName)")
	public Person findPersonByUserName(@Param("userName") String userName);
	
	@Query("SELECT p from Person p WHERE UPPER(userName) = UPPER(:userName) and encPassword = :password")
	public Person checkCredentials(@Param("userName") String userName, @Param("password") String password);
	
	public List<Person> findByLastName(@Param("lastName") String lastName);

	@Query("SELECT p from Person p")
	public List<Person> findAllPeople();

	@Query("SELECT p from Person p WHERE id=:id")
	public Person findPersonById(@Param("id")Integer id);

//	@Query("SELECT p from Person p WHERE birthDate  =:id")
//	public Person findPersonBirthday(@Param("")Integer id);	

}