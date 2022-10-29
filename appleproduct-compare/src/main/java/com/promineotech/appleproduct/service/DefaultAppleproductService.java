package com.promineotech.appleproduct.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.appleproduct.dao.AppleproductCompareDao;
import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultAppleproductService implements AppleproductService {

	@Autowired
	private AppleproductCompareDao appleproductCompareDao;

	@Transactional(readOnly = true)
	@Override
	public List<Appleproduct> fetchAppleproduct(AppleproductModel model, String color) {
		log.info("The fetchAppleproduct method was called with model={} and color={}", model, color);

		List<Appleproduct> appleproduct = appleproductCompareDao.fetchAppleproduct(model, color);

		
		if(appleproduct.isEmpty()) {
			String msg = String.format("No apple products were found with model=%s and color=%s",
					model, color);
			
		throw new NoSuchElementException(msg);	
		
		}
		Collections.sort(appleproduct);
		return appleproduct;
	}

}
