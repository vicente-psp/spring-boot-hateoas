package com.vicente.springboothateoas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_order")
public class Order extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long idOrder;

	private UUID uuid;

	@Column(name = "creation_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@OneToOne (cascade=CascadeType.ALL)
	private People people = new People();

	@OneToMany(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private List<Product> products = new ArrayList<Product>();

	private BigDecimal sumOrder() {
		return products.stream().map(Product::getValue).reduce(BigDecimal.ZERO, BigDecimal::add); 

	}
}