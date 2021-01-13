package com.howtodoinjava.example.sampleservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.example.async.model.EmployeeName;
import com.howtodoinjava.example.async.model.EmployeeNames;
import com.howtodoinjava.example.async.model.EmployeePhone;

@RestController
public class EmployeeDataController {
	private static Logger log = LoggerFactory.getLogger(EmployeeDataController.class);

	@RequestMapping(value = "/names", method = RequestMethod.GET)
	public EmployeeNames getEmployeeName() {
		log.info("GET /names Start");

		EmployeeNames employeeNamesList = new EmployeeNames();

		EmployeeName employeeName1 = new EmployeeName();
		EmployeeName employeeName2 = new EmployeeName();

		List<EmployeeName> employeeList = new ArrayList<EmployeeName>();
		{
			employeeName1.setFirstName("Santa");
			employeeName1.setLastName("Singh");
		}
		{
			employeeName2.setFirstName("Banta");
			employeeName2.setLastName("Singh");
		}

		employeeList.add(employeeName1);
		employeeList.add(employeeName2);

		employeeNamesList.setEmployeeNameList(employeeList);

		return employeeNamesList;
	}

	@RequestMapping(value = "/phones", method = RequestMethod.GET)
	public EmployeePhone getPhoneNumbers() {
		log.info("GET /phones Start");

		EmployeePhone employeePhone = new EmployeePhone();
		{
			ArrayList<String> phoneNumberList = new ArrayList<String>();

			phoneNumberList.add("100000");
			phoneNumberList.add("200000");

			employeePhone.setPhoneNumbers(phoneNumberList);
		}

		return employeePhone;
	}
}