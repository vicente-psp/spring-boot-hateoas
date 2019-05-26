package com.vicente.springboothateoas.entities;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//import black.door.hate.HalRepresentation;
//import black.door.hate.HalResource;
import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_people")
public class People extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long idPeople;

	@NotBlank(message = "Nome é obrigatório")
	@Column(nullable = false)
	private String name;

	@Column(name = "sur_name")
	private String surName;

	@Column
	@Email(message = "Endereço de email inválido")
	private String email;

	@Column(name = "creation_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@OneToMany(fetch=FetchType.LAZY)
	private List<Order> orders = new ArrayList<Order>();

}