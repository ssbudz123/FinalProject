package com.promineotech.appleproduct.controller.support;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;

public class FetchAppleproductTestSupport extends BaseTest {

protected List<Appleproduct> buildExpected() {
		
	List<Appleproduct> list = new LinkedList<>();
	
	// @formatter:off
	list.add(Appleproduct.builder()
			.modelID(AppleproductModel.IPHONE14PRO)
			.color("Purple")
			.basePrice(new BigDecimal("999"))
			.cpuGen(16)
			.storageAvail(128)
			.build());
	
//	list.add(Appleproduct.builder()
//			.modelID(AppleproductModel.IPHONE13)
//			.color("Green")
//			.basePrice(new BigDecimal("699"))
//			.cpuGen(15)
//			.storageAvail(128)
//			.build());
//	
//	list.add(Appleproduct.builder()
//			.modelID(AppleproductModel.IPAD)
//			.color("Yellow")
//			.basePrice(new BigDecimal("449"))
//			.cpuGen(14)
//			.storageAvail(64)
//			.build());
//	
//	list.add(Appleproduct.builder()
//			.modelID(AppleproductModel.IPADPRO)
//			.color("Silver")
//			.basePrice(new BigDecimal("799"))
//			.cpuGen(2)
//			.storageAvail(128)
//			.build());
	// @formatter:on
	Collections.sort(list);
	return list;
	}

/**
 * 
 * @param error
 * @param status
 */

protected void assertErrorMessageValid(Map<String, Object> error, HttpStatus status) {
	// @formatter:off
	assertThat(error)
	.containsKey("message")
	.containsEntry("status code", status.value())
	.containsEntry("uri", "/appleproducts/")
	.containsKey("timestamp")
	.containsEntry("reason", status.getReasonPhrase());
    // @formatter:on
}
}
