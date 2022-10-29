package com.promineotech.appleproduct.entity;

import java.math.BigDecimal;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appleproduct implements Comparable<Appleproduct>{

	private Long modelPK;
	private AppleproductModel modelID;
	private String color;
	private BigDecimal basePrice;
	private int cpuGen;
	private int storageAvail;
	
	
	@JsonIgnore
	public Long getModelPK() {
		return modelPK;
	}


	@Override
	public int compareTo(Appleproduct that) {
		// @formatter:off
		
		return Comparator
				     .comparing(Appleproduct::getModelID)
				     .thenComparing(Appleproduct::getColor)
				     .thenComparing(Appleproduct::getBasePrice)
				     .thenComparing(Appleproduct::getCpuGen)
				     .thenComparing(Appleproduct::getStorageAvail)
				     .compare(this, that);
		
		// @formatter:on
	}
}

