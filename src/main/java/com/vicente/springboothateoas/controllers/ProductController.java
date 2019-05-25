package com.vicente.springboothateoas.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import com.vicente.springboothateoas.entities.Product;
import com.vicente.springboothateoas.interfaces.GenericOperationsController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;



public class ProductController implements GenericOperationsController<Product> {

	@Override
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
				 produces = {MediaType.APPLICATION_JSON_VALUE,
						 	 MediaType.APPLICATION_XML_VALUE,
						 	 MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Product> post(Product product) {
		// TODO Auto-generated method stub
		Link link = linkTo(ProductController.class).slash(product.getId()).withSelfRel();
		Resource<Product> result = new Resource<Product>(product,link);
		return result;
	}

	@Override
	public void put(Product entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Resources<Product> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource<Product> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void patch(Product entity) {
		// TODO Auto-generated method stub
		
	}

}