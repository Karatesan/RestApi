package com.karatesan.RESTAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karatesan.RESTAPI.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
