package com.promineotech.appleproduct.dao;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;
import com.promineotech.appleproduct.entity.Color;
import com.promineotech.appleproduct.entity.Customer;
import com.promineotech.appleproduct.entity.Order;

public interface AppleproductOrderDao {

	Optional<Customer> fetchCustomer(String customerId);

	Optional<Appleproduct> fetchModel(AppleproductModel model, String color);
	
	Optional<Color> fetchColor(String colorId);

	Order saveOrder(Customer customer, Appleproduct appleproduct, Color color, BigDecimal price);

	boolean deleteOrder(String orderId);

	boolean updateOrder(Order order);

}