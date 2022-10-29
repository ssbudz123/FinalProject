package com.promineotech.appleproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;
import com.promineotech.appleproduct.service.AppleproductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicAppleproductsController implements AppleproductController {
	
	@Autowired
	private AppleproductService appleproductService;

	@Override
	public List<Appleproduct> fetchAppleproducts(AppleproductModel model, String color) {
		log.debug("model={},color={}", model, color);
		return appleproductService.fetchAppleproduct(model, color);
	}

}
