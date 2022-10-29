package com.promineotech.appleproduct.dao;

import java.util.List;

import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;

public interface AppleproductCompareDao {

	
	/**
	 * 
	 * @param model
	 * @param color
	 * @return
	 */
	List<Appleproduct> fetchAppleproduct(AppleproductModel model, String color);

}
