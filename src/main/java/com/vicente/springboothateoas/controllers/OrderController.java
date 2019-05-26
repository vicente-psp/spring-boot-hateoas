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

import javax.validation.Valid;


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
	public Resource<Order> post(@RequestBody @Valid Order entity) {
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
	public void put(@RequestBody @Valid Order entity) {
		try {
			service.put(entity);
			logger.info(String.format("Registro atualizado: %s", entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método PUT.\nMensagem: %s", e.getMessage()));
		}
	}

	@Override
	@DeleteMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Order entity) {
		try {
			service.delete(entity);
			logger.info(String.format("Registro(s) deletado(s): %s",entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método PUT.\nMensagem: %s", e.getMessage()));
		}
	}

	@Override
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.APPLICATION_XML_VALUE,
							 MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<Order> get() {
		try {
			List<Order> entities = service.get();

			logger.info(String.format("Registro(s) recuperados(s): %s", entities.toString()));

			for (final Order entity : entities) {
		        Link selfLink = linkTo(OrderController.class)
		        		.slash(entity.getIdOrder())
		        		.withSelfRel();

		        Link selfLinkPeople = linkTo(PeopleController.class)
		        		.slash(entity.getPeople().getIdPeople())
		        		.withSelfRel();

				entity.getPeople().add(selfLinkPeople);
				entity.add(selfLink);
		    }
			Link link = linkTo(OrderController.class).withSelfRel();
			return new Resources<>(entities, link);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método GET.\nMensagem: %s", e.getMessage()));
		}
		return null;
	}

	@Override
	@GetMapping(value="/{id}",
				consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
	 			produces = {MediaType.APPLICATION_JSON_VALUE,
	 						MediaType.APPLICATION_XML_VALUE,
	 						MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<Order> get(@PathVariable Long id) {
		try {
			Order entity = service.get(id);
			logger.info(String.format("Registro recuperado: %s", entity.toString()));

			Link link = linkTo(OrderController.class).slash(entity.getIdOrder()).withSelfRel();
			return new Resource<>(entity, link);
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método GET.\nMensagem: %s", e.getMessage()));
		}
		return null;
	}

	@Override
	@PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(@RequestBody Order entity) {
		try {
			service.patch(entity);
			logger.info(String.format("Registro atualizado: %s",entity.toString()));
		} catch (Exception e) {
			logger.error(String.format("Erro ao executar o método PATCH.\nMensagem: %s", e.getMessage()));
		}
	}

}