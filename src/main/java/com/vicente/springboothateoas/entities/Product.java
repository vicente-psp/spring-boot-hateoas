package com.vicente.springboothateoas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tb_product")
public class Product extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long idProduct;

	@NotBlank(message = "Nome do produto é obrigatório")
	@Column
	private String name;

	@Column
	private String description;

	@Column
	private BigDecimal value;

	@Column(name = "creation_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

}