package com.karatesan.RESTAPI.modelAssemblersHATEOAS;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.karatesan.RESTAPI.controllers.OrderController;
import com.karatesan.RESTAPI.model.Order;
import com.karatesan.RESTAPI.utilityClasses.Status;


@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

	@Override
	public EntityModel<Order> toModel(Order order) {
		
		EntityModel<Order> orderModel = EntityModel.of(order,
				  WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).one(order.getId())).withSelfRel(),
				  WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).all()).withRel("orders"));
		
		if(order.getStatus()==Status.IN_PROGRESS) {
			orderModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
			orderModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
		}
		
		
		return orderModel;
	}
	
	
	
	

}
