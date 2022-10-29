package com.promineotech.appleproduct.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.appleproduct.entity.Order;
import com.promineotech.appleproduct.entity.OrderRequest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Apple Products Order Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.") })

public interface AppleproductOrderController {
	

	// @formatter:off
	@Operation(
		summary = "Create an order for an Apple product",
		description = "Returns the created Apple product",
		responses = {
			@ApiResponse(
					responseCode = "201",
					description = "The created Apple product is returned.",
					content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = Order.class))),	
			@ApiResponse(
					responseCode = "400",
					description = "The request parameters are invalid.",
					content = @Content(mediaType = "application/json")),	
			@ApiResponse(
					responseCode = "404",
					description = "An Apple product component was not found with the input criteria.",
					content = @Content(mediaType = "application/json")),	
			@ApiResponse(
					responseCode = "500",
					description = "An unplanned error occurred.",
					content = @Content(mediaType = "application/json"))
		},
		parameters = {
				@Parameter(
						name = "orderRequest",
						required = true,
						description = "The order as JSON")
		}
			
			)
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
Order createOrder(@Valid @RequestBody OrderRequest orderRequest);
	
	@DeleteMapping("/order")
	Map<String, Object> deleteOrder(@RequestParam String orderId);
	
	
	@PutMapping("/order")
	Order updateOrder(@RequestBody Order order);
		
		
		
}
