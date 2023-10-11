package com.karatesan.RESTAPI.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.karatesan.RESTAPI.exceptions.EmployeeNotFoundException;
import com.karatesan.RESTAPI.model.Employee;
import com.karatesan.RESTAPI.modelAssemblersHATEOAS.EmployeeModelAssembler;
import com.karatesan.RESTAPI.repositories.EmployeeRepository;

@RestController
public class EmployeeController {

  private final EmployeeRepository repository;
  private final EmployeeModelAssembler assembler;

  public EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
	super();
	this.repository = repository;
	this.assembler = assembler;
}

//-------------------------------------------------------------------------------------------------------------------------------------------------
 
  @GetMapping("/employees")
  public CollectionModel<EntityModel<Employee>> all(){
	  
	  List<EntityModel<Employee>> employees = repository.findAll().stream()
			  .map(assembler::toModel).collect(Collectors.toList()); //equals: employee->assembler.toModel(employee)
	  
	return CollectionModel.of(employees,WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).all()).withSelfRel());  
  }
  
//-------------------------------------------------------------------------------------------------------------------------------------------------
  
  @PostMapping("/employees")
  public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
	  EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));
	  
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }

//-------------------------------------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/employees/{id}")
  public EntityModel<Employee> one(@PathVariable Long id) {

    Employee employee = repository.findById(id) //
        .orElseThrow(() -> new EmployeeNotFoundException(id)); 
    return assembler.toModel(employee);
  }
  
//-------------------------------------------------------------------------------------------------------------------------------------------------

  @PutMapping("/employees/{id}")
  public ResponseEntity<?>  replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    
	  Employee updatedEmployee = repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
	  System.out.println(updatedEmployee);
	  EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);
	  return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }
  
//-------------------------------------------------------------------------------------------------------------------------------------------------

  @DeleteMapping("/employees/{id}")
  public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
