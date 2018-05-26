package com.danielev86.domxml.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.danielev86.domxml.bean.PersonBean;

@Repository
public class PersonRepository {
	
	public List<PersonBean> getPersons(){
		List<PersonBean> lstPerson = new ArrayList<PersonBean>();
		
		PersonBean personBean = new PersonBean();
		personBean.setAge(34);
		personBean.setFirstName("Vincenzo");
		personBean.setLastName("Nibali");
		
		lstPerson.add(personBean);
		
		personBean = new PersonBean();
		personBean.setAge(28);
		personBean.setFirstName("Tom");
		personBean.setLastName("Dumolin");
		lstPerson.add(personBean);
		
		
		return lstPerson;
	}

}
