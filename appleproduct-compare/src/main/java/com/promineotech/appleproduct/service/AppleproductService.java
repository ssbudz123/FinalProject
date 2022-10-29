package com.promineotech.appleproduct.service;

import java.util.List;

import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;

public interface AppleproductService {

	/**
	 * 
	 * @param model
	 * @param color
	 * @return
	 */
	List<Appleproduct> fetchAppleproduct(AppleproductModel model, String color);

}
