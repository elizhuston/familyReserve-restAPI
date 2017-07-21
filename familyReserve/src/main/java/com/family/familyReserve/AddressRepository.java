package com.family.familyReserve;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository  extends JpaRepository<Address, Integer>{

	@Query("SELECT a from Address a WHERE person.id = :id")
	public List<Address> findPersonAddress(@Param("id")Integer id);


	
}
