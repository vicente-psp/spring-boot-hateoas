package com.vicente.springboothateoas.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import com.vicente.springboothateoas.services.impl.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vicente.springboothateoas.entities.Order;
import com.vicente.springboothateoas.interfaces.GenericOperationsController;


@RestController
@RequestMapping("/orders")
public class OrderController implements GenericOperationsController<Order> {

	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired private OrderServiceImpl service;
	
	@Override
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
				 produces = {MediaType.APPLICATION_JSON_VALUE,
						 	 MediaType.APPLICATION_XML_VALUE,
						 	 MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Order> post(@RequestBody Order entity) {
		try {
			service.post(entity);
			logger.info("Registro inserido");

			Link link = linkTo(OrderController.class).slash(entity.getIdOrder()).withSelfRel();
			Resource<Order> result = new Resource<Order>(entity, link);
			return result;
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método POST.\nMensagem: %s", e.getMessage()));
		}
		return null;
	}

	@Override
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@RequestBody Order order) {
		try {
			service.put(order);
			logger.info(String.format("Registro atualizado: %s", order.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método PUT.\nMensagem: %s", e.getMessage()));
		}
	}

	@Override
	@DeleteMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Order orders) {
		try {
			service.delete(orders);
			logger.info(String.format("Registro(s) deletado(s): %s",orders.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método PUT.\nMensagem: %s",e.getMessage()));
		}

	}

	@Override
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.APPLICATION_XML_VALUE,
							 MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<Order> get() {
	 	Resources<Order> result = null;
		try {
			List<Order> orders = service.get();

			logger.info(String.format("Registro(s) recuperados(s): %s",orders.toString()));

			for (final Order order : orders) {
		        Link selfLink = linkTo(OrderController.class)
		        		.slash(order.getIdOrder())
		        		.withSelfRel();

		        Link selfLinkPeople = linkTo(PeopleController.class)
		        		.slash(order.getPeople().getIdPeople())
		        		.withSelfRel();

		        order.getPeople().add(selfLinkPeople);
		        order.add(selfLink);
		    }
			Link link = linkTo(OrderController.class).withSelfRel();
			result = new Resources<Order>(orders, link);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
		}
		return result;
	}

	@Override
	@GetMapping(value="/{id}",
				consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
	 			produces = {MediaType.APPLICATION_JSON_VALUE,
	 						MediaType.APPLICATION_XML_VALUE,
	 						MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<Order> get(@PathVariable("id") Long id) {

		Resource<Order> result = null;
		try {
			Order order = service.get(id);
			logger.info(String.format("Registro recuperado: %s",order.toString()));

			Link link = linkTo(OrderController.class).slash(order.getIdOrder()).withSelfRel();
			result = new Resource<>(order, link);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método GET.\nMensagem: %s", e.getMessage()));
		}
		return result;

//		try {
//			Order order = service.get(id);
//			logger.info(String.format("Registro recuperado: %s",order.toString()));
//		} catch (Exception e) {
//			logger.error(String.format("Erro ao executar o método GET.\nMensagem: %s",e.getMessage()));
//		}
//		return null;
	}

	@Override
	@PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(@RequestBody Order order) {
		try {
			service.patch(order);
			logger.info(String.format("Registro atualizado: %s",order.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método PATCH.\nMensagem: %s",e.getMessage()));
		}

	}

}