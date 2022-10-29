package com.promineotech.appleproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.promineotech.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = { ComponentScanMarker.class })

public class AppleproductCompare {

	public static void main(String[] args) {
		SpringApplication.run(AppleproductCompare.class, args);

	}

}
