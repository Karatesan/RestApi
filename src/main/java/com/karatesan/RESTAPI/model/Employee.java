package com.karatesan.RESTAPI.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue  
	private Long id;
	private String firstName;
	private String lastName;
	private String role;
	
	
	Employee (){}
	
//When i send data in POST: {"name": "Samwise Gamgee", "role": "gardener"}, 
//spring creates empty objedt with default constructyor and then fill it with setters
//curl -v POST localhost:8080/employees -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "role": "gardener"}' - for bash
//-H - header, -d - data
// -v POST - pritns aditional data, liek headers etc. Helpful for debugging	
//-X POST/PUT/GET - oznaczenie requesta, w POST mozna pominac jak mamy -d z danymi
	public Employee(String firstName, String lastName, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}


	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		String[] parts = name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}	

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, role);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(role, other.role);
	}

	

}
