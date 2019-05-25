package com.vicente.springboothateoas.entities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//import black.door.hate.HalRepresentation;
//import black.door.hate.HalResource;
import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People extends ResourceSupport {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long idPeople;

	private String name;

	private String surName;

	private String email;

	@OneToMany(fetch=FetchType.LAZY)
	private List<Order> orders = new ArrayList<Order>();

//	@Override
//	public HalRepresentation.HalRepresentationBuilder representationBuilder() {
//		return HalRepresentation.builder()
//				.addProperty("nae", name)
//				.addProperty("surName", surName)
//				.addProperty("email", email)
//				.addLink("self", this);
//	}
//
//	@Override
//	public URI location() {
//		try {
//			return new URI("/orders/" + idPeople);
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}