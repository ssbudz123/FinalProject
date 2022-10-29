package com.promineotech.appleproduct.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.appleproduct.dao.AppleproductOrderDao;
import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.Color;
import com.promineotech.appleproduct.entity.Customer;
import com.promineotech.appleproduct.entity.Order;
import com.promineotech.appleproduct.entity.OrderRequest;
import com.promineotech.appleproduct.dao.DefaultAppleproductOrderDao;


@Service
public class DefaultAppleproductOrderService implements AppleproductOrderService {

	
	@Autowired
	private AppleproductOrderDao appleproductOrderDao;
	
	
	@Transactional
	@Override
	public Order createOrder(OrderRequest orderRequest) {
		
		Customer customer = getCustomer(orderRequest);
		
		Appleproduct appleproduct = getModel(orderRequest);
						
		
		Color color = getColor(orderRequest);
		
		BigDecimal price = appleproduct.getBasePrice();
		
		return appleproductOrderDao.saveOrder(customer, appleproduct, color, price);
		
		
	}

/**
 * 
 * @param orderRequest
 * @return
 */
	protected Color getColor(OrderRequest orderRequest) {
		return appleproductOrderDao.fetchColor(orderRequest.getColor())
				.orElseThrow(() -> new NoSuchElementException("Color with ID=" + 
				orderRequest.getColor() + " was not found"));
	}
/**
 * 
 */

	protected Appleproduct getModel(OrderRequest orderRequest) {
		return appleproductOrderDao
				.fetchModel(orderRequest.getModel(), orderRequest.getColor())
				.orElseThrow(() -> new NoSuchElementException("Model with ID=" + 
						orderRequest.getModel() + " was not found."));
	}
/**
 * 
 * @param orderRequest
 * @return
 */

	protected Customer getCustomer(OrderRequest orderRequest) {
		return appleproductOrderDao.fetchCustomer(orderRequest.getCustomer())
				.orElseThrow(() -> new NoSuchElementException("Customer with ID=" + 
		orderRequest.getCustomer() + " was not found"));
	}

	@Transactional(readOnly = false)
    @Override
public void deleteOrder(String orderId) {
	if(!appleproductOrderDao.deleteOrder(orderId)) {
		throw new NoSuchElementException("Order with ID=" + orderId + " does not exist.");
	}
	
}

	@Override
	public Order updateOrder(Order order) {
		if(!appleproductOrderDao.updateOrder(order)) {
			throw new NoSuchElementException("Order with ID=" + order.getOrderId() + " does not exist.");
		
	}
return order;
	}
		
}
