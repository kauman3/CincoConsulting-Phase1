package com.mgg;

import java.util.Collections;
import java.util.List;


/**
 * This class models a Person with a personCode, type, firstName, lastName, and address.
 * 
 * @author kauman<br \>
 * Kyle Auman<br \>
 * kauman3@huskers.unl.edu<br \>
 * CSCE156<br \><br \>
 * @author zmain<br \>
 * Zach Main<br \>
 * zmain2@huskers.unl.edu<br \>
 * CSCE156<br \>
 *
 */
public class Person {
	
	private String personCode;
	private String type;
	private String firstName;
	private String lastName;
	private Address address;
	private List<String> emails;
	
	public Person(String personCode, String type, String firstName, String lastName, Address address, List<String> emails) {
		this.personCode = personCode;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emails = emails;
	}
	
	public Person(String code, String type, String firstName, String lastName, Address address) {
		this(code, type, firstName, lastName, address, Collections.emptyList());
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return this.personCode;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * @return the emails
	 */
	public List<String> getEmails() {
		return this.emails;
	}
	
}
