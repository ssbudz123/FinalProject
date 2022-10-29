package com.promineotech.appleproduct.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.appleproduct.Constants;
import com.promineotech.appleproduct.controller.support.FetchAppleproductTestSupport;
import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;
import com.promineotech.appleproduct.service.AppleproductService;



class FetchAppleproductTest  {
	
	@Nested
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles("test")
	@Sql(
			scripts = 
	           {"classpath:flyway/migrations/apple_product_Schema.sql",
			   "classpath:flyway/migrations/apple_product_Data.sql"},
	        config = @SqlConfig(encoding = "utf-8"))

	

	class TestsThatDoNotPolluteTheApplicationContext extends FetchAppleproductTestSupport {
		@Test
		void testThatAnErrorMessageIsReturnedWhenAnUnknownColorIsSupplied() {
			// Given: a valid model, color and URI
			
			AppleproductModel model = AppleproductModel.IPHONE14PRO;
			String color = "Unknown Color";
			String uri = String.format("%s/?model=%s&color=%s", getBaseUriForAppleproduct(), model, color);
		
			
			// When: a connection is made to the URI 
			
			ResponseEntity<Map<String, Object>> response = 
					getRestTemplate().exchange(uri, HttpMethod.GET, null, 
							new ParameterizedTypeReference<>() {});
			
			// Then: a not found (404) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
			
			// And: an error message is returned 
			
			Map<String, Object> error = response.getBody();
			assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
		}

		@Test
		void testThatAppleproductsAreReturnedWhenAValidModelAndColorAreSupplied() {
			// Given: a valid model, color and URI
			
			AppleproductModel model = AppleproductModel.IPHONE14PRO;
			String color = "Purple";
			String uri = String.format("%s/?model=%s&color=%s", getBaseUriForAppleproduct(), model, color);
		
			
			// When: a connection is made to the URI 
			
			ResponseEntity<List<Appleproduct>> response = 
					getRestTemplate().exchange(uri, HttpMethod.GET, null, 
							new ParameterizedTypeReference<>() {});
			
			// Then: a success (OK - 200) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			
			// And: the actual list is the same as the expected list
			List<Appleproduct> actual = response.getBody();
			List<Appleproduct> expected = buildExpected();
			
			
			
			assertThat(actual).isEqualTo(expected);
		
		}
		@ParameterizedTest
		@MethodSource("com.promineotech.appleproduct.controller.FetchAppleproductTest#parametersForInvalidInput")
		void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied(
				String model, String color, String reason) {
			// Given: a valid model, color and URI
			
			
			String uri = String.format("%s/?model=%s&color=%s", getBaseUriForAppleproduct(), model, color);
			
			
			// When: a connection is made to the URI 
			
			ResponseEntity<Map<String, Object>> response = 
					getRestTemplate().exchange(uri, HttpMethod.GET, null, 
							new ParameterizedTypeReference<>() {});
			
			// Then: a bad request (400) status code is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			
			// And: an error message is returned 
			
			Map<String, Object> error = response.getBody();
			assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
		}
	}
	static Stream<Arguments> parametersForInvalidInput() {
		// @formatter:off
		return Stream.of(
				arguments("IPHONE14PRO", "#$1223", "Color contains non-alpha-numeric characters."),
				arguments("IPHONE14PRO", "P".repeat(Constants.COLOR_MAX_LENGTH + 1), "Color length too long."),
				arguments("INVALID", "Orange", "Color is not enum value")
		// @formatter:on
				);
	}
	
	@Nested
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@ActiveProfiles("test")
	@Sql(
			scripts = 
	           {"classpath:flyway/migrations/apple_product_Schema.sql",
			   "classpath:flyway/migrations/apple_product_Data.sql"},
	        config = @SqlConfig(encoding = "utf-8"))

	
class TestsThatPolluteTheApplicationContext extends FetchAppleproductTestSupport {
		
		@MockBean
		private AppleproductService appleproductService;
		
		

		 @Test
		  
			
		void testThatAnUnplannedErrorResultsInA500Status() {
				
				
			// Given: a valid model, color and URI
				
			AppleproductModel model = AppleproductModel.IPHONE14PRO;
			String color = "Invalid";
			String uri = 
					String.format
					("%s?model=%s&color=%s", getBaseUriForAppleproduct(), model, color);
					doThrow(new RuntimeException("Yelp!")).when(appleproductService)
					.fetchAppleproduct(model, color);
					   
					   
			// When: a connection is made to the URI 
			ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri,
				 HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
					   
					   
		    // Then: an internal server error (500) status is returned
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
					   
		   // And: an error message is returned
			 Map<String, Object> error = response.getBody();
					  
			assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
			
		
		
	}
}
}
	
	
	
	

	

