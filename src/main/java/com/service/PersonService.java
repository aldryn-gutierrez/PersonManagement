package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.model.Person;
import com.repositories.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository pr;
	 
    @Autowired
    public void setPersonRepository(PersonRepository pr) {
        this.pr = pr;
    }
	
	public List<Person> listAll() {
		return (List<Person>) pr.findAll();	
	}
	
	public List<Person> getByName(String name) {
		
		String[] explodedName = name.split(" ");
		
		Person p = new Person();
		if (explodedName.length == 1) {
			p.setFirstname(explodedName[0]);
			p.setLastname(explodedName[0]);
		} else {
			p.setLastname(explodedName[explodedName.length-1]);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i != explodedName.length-1; i++) {
				sb.append(explodedName[i]);
			}
			p.setFirstname(sb.toString());
		}
		
		return pr.findByFirstnameContainingOrLastnameContaining(p.getFirstname(), p.getLastname());
	}
	
	public Person saveOrUpdate(Person p) {
		return pr.save(p);
	}

}
