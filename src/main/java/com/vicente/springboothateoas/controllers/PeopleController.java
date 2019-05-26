package com.vicente.springboothateoas.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.vicente.springboothateoas.services.impl.PeopleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.vicente.springboothateoas.entities.People;
import com.vicente.springboothateoas.interfaces.GenericOperationsController;

import javax.validation.Valid;


@RestController
@RequestMapping("/peoples")
public class PeopleController implements GenericOperationsController<People> {

	Logger logger = LoggerFactory.getLogger(PeopleController.class);

	@Autowired private PeopleServiceImpl service;

	@Override
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<People> post(@RequestBody @Valid People entity) {
		try {
			service.post(entity);
			logger.info("Registro inserido");

			Link link = linkTo(OrderController.class).slash(entity.getIdPeople()).withSelfRel();
			return new Resource<>(entity, link);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método POST.\nMensagem: %s", e.getMessage()));
		}
		return null;
	}

	@Override
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public void put(@RequestBody People people) {
		logger.info("Atulizado registro de pessoa.");
		
	}

	@Override
	public void delete(People entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.APPLICATION_XML_VALUE,
							 MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<People> get() {
		try {
			//List<Order> orders = orderService.get();
			
			//logger.info(String.format("Registro(s) recuperados(s): %s",orders.toString()));
			
//			for (final Order order : orders) {
//		        Link selfLink = linkTo(methodOn(OrderController.class)
//		          .getOrderById(customerId, order.getIdOrder()())).withSelfRel();
//		        order.add(selfLink);
//		    }
//		  
//		    Link link = linkTo(methodOn(CustomerController.class)
//		      .getOrdersForCustomer(customerId)).withSelfRel();
//		    Resources<Order> result = new Resources<Order>(orders, link);
//		    return result;
			
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
		}
		return null;
	}

	@Override
	public Resource<People> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void patch(People entity) {
		// TODO Auto-generated method stub
		
	}

}