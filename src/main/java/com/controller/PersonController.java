package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.model.Person;
import com.service.PersonService;
import com.utilities.InputValidationUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PersonController {
	
	@Autowired
	private PersonService ps;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {
		return "homeview";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getRegFormPage(ModelMap model) {
		model.addAttribute("person", new Person());
		return "addpersonview";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String processRegFormPage(
			ModelMap model,  
			@ModelAttribute("person") Person p, 
			BindingResult result, 
			SessionStatus status, 
			HttpServletRequest request
	) throws Exception {
		// Prepare a Map with fields to Validate
		Map<String, String> fieldsToValidate = new HashMap<String, String>();
		fieldsToValidate.put("First Name", p.getFirstname());
		fieldsToValidate.put("Last Name", p.getLastname());
		
		// Validate the Input Fields
		InputValidationUtil validator = new InputValidationUtil();		
		validator.checkIfStringEmpty(fieldsToValidate);
		
		if (!validator.containsError()) {
			ps.saveOrUpdate(p);
			model.addAttribute("message", "User data saved!");
			model.addAttribute("person", new Person());
		} else {
			model.addAttribute("error", validator.errors());
		}

		return "addpersonview";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String getSearchEmployeePage() {
		return "searchview";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String processSearchEmployeePage(
			@RequestParam(value="searchText", required=false) String searchText,
			ModelMap model
	) throws Exception {
		//Prepare A Map with fields to Validate
		Map<String, String> fieldsToValidate = new HashMap<String, String>();
		fieldsToValidate.put("Searched Name", searchText);
		
		// Validate the Input Fields
		InputValidationUtil validator = new InputValidationUtil();		
		validator.checkIfStringEmpty(fieldsToValidate);
		
		if (!validator.containsError()) {
			List<Person> persons = ps.getByName(searchText);
			model.addAttribute("persons", persons);
		} else {
			model.addAttribute("error", validator.errors());
		}
		
		return "searchview";
	}
}
