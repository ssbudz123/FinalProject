package com.promineotech.appleproduct.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.appleproduct.entity.Order;
import com.promineotech.appleproduct.entity.OrderRequest;
import com.promineotech.appleproduct.service.AppleproductOrderService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class BasicAppleproductOrderController implements AppleproductOrderController {

	
	@Autowired
	private AppleproductOrderService appleproductOrderService;
	@Override
	public Order createOrder(OrderRequest orderRequest) {
		log.debug("Order={}", orderRequest);
		return appleproductOrderService.createOrder(orderRequest);
	}
	@Override
	public Map<String, Object> deleteOrder(String orderId) {
		appleproductOrderService.deleteOrder(orderId);
		return Map.of("message", "Order with ID=" + orderId + " successfully deleted");
		
	}
	@Override
	public Order updateOrder(Order order) {
		log.info("Updating order {}", order);
		return appleproductOrderService.updateOrder(order);
	}

}
