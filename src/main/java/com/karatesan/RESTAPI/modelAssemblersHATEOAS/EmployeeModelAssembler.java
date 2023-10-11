package com.karatesan.RESTAPI.modelAssemblersHATEOAS;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.karatesan.RESTAPI.controllers.EmployeeController;
import com.karatesan.RESTAPI.model.Employee;



@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

	@Override
	public EntityModel<Employee> toModel(Employee entity) {
		
		return EntityModel.of(entity,
				  WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).one(entity.getId())).withSelfRel(),
				  WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).all()).withRel("employees"));

	}

}
