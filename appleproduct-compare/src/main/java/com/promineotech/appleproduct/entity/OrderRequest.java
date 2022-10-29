package com.promineotech.appleproduct.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class OrderRequest {

	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")
	
	private String customer;
	
	@NotNull
	private AppleproductModel model;
	
	@NotNull
	@Length(max = 30)
	@Pattern(regexp = "[\\w\\s]*")
	private String color;
	
}
