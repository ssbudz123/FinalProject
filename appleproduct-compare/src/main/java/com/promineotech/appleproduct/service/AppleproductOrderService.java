package com.promineotech.appleproduct.service;

import com.promineotech.appleproduct.entity.Order;
import com.promineotech.appleproduct.entity.OrderRequest;

public interface AppleproductOrderService {

	Order createOrder(OrderRequest orderRequest);

	void deleteOrder(String orderId);

	Order updateOrder(Order order);

}
