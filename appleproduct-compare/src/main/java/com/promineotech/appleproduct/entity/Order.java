package com.promineotech.appleproduct.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

	private Long orderPK;
	private Customer customer;
	private Appleproduct model;
	private Color color;
	
	@JsonIgnore
	public Long getOrderPK() {
		return orderPK;
	}

	public String getOrderId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCategory() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
