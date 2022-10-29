package com.promineotech.appleproduct.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.appleproduct.controller.support.CreateOrderTestSupport;
import com.promineotech.appleproduct.entity.AppleproductModel;
import com.promineotech.appleproduct.entity.Order;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
		scripts = 
           {"classpath:flyway/migrations/apple_product_Schema.sql",
		   "classpath:flyway/migrations/apple_product_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
class CreateOrderTest extends CreateOrderTestSupport{

	
	/**
	 * 
	 */
	@Test
	void testCreateOrderReturnsSuccess201() {
		// Given: an order as JSON
		String body = createOrderBody();
		String uri = getBaseUriForOrders();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
		
		// When: the order is sent
		ResponseEntity<Order> response = getRestTemplate().exchange(uri, HttpMethod.POST,
				bodyEntity, Order.class);
		
		
		// Then: a 201 status is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		
		// And: the returned order is correct
	assertThat(response.getBody()).isNotNull();
	
	Order order = response.getBody();
	assertThat(order.getCustomer().getCustomerId()).isEqualTo("BUDZINSKI_SHAWN");
	assertThat(order.getModel().getModelID()).isEqualTo(AppleproductModel.IPHONE14PRO);
	assertThat(order.getColor().getColorId()).isEqualTo("Purple");
	}

	

}
