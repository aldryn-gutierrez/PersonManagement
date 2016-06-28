package com.repositories;

import com.model.Person;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person, Long> {
	
	@Query("SELECT p FROM Person p WHERE p.firstname LIKE %:firstname% OR "
			+ "p.firstname LIKE %:lastname% OR "
			+ "p.lastname LIKE %:lastname% OR "
			+ "p.lastname LIKE %:firstname%")
	public List<Person> findByFirstnameContainingOrLastnameContaining(
		@Param(value = "firstname") String firstname, 
		@Param(value = "lastname") String lastname
	);
}
