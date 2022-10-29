package com.promineotech.appleproduct.controller;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.appleproduct.Constants;
import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/appleproducts")
@OpenAPIDefinition(info = @Info(title = "Apple Products Compare Service"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.") })

public interface AppleproductController {
	

	// @formatter:off
	@Operation(
		summary = "Returns a list of Appleproducts",
		description = "Returns a list of Appleproducts given an optional model and/or color",
		responses = {
			@ApiResponse(
					responseCode = "200",
					description = "A list of Appleproducts is returned.",
					content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = Appleproduct.class))),	
			@ApiResponse(
					responseCode = "400",
					description = "The request parameters are invalid.",
					content = @Content(mediaType = "application/json")),	
			@ApiResponse(
					responseCode = "404",
					description = "No Appleproducts were found with the input criteria.",
					content = @Content(mediaType = "application/json")),	
			@ApiResponse(
					responseCode = "500",
					description = "An unplanned error occurred.",
					content = @Content(mediaType = "application/json"))
		},
		parameters = {
				@Parameter(
						name = "model",
						allowEmptyValue = false,
						required = false,
						description = "The model name (example... 'IPHONE14PRO')"), 
				@Parameter(
						name = "color",
						allowEmptyValue = false,
						required = false,
						description = "The color (example... 'Purple')")
		}
			
			)
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
List<Appleproduct> fetchAppleproducts(
		
		
		@RequestParam(required = false)
		AppleproductModel model,
		@Length(max = Constants.COLOR_MAX_LENGTH)
		@Pattern(regexp = "[\\w\\s]*")
		@RequestParam(required = false)
		String color);
// @formatter:on
}
