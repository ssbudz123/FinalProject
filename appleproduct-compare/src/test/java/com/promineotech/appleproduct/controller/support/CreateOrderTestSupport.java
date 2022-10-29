package com.promineotech.appleproduct.controller.support;

public class CreateOrderTestSupport extends BaseTest{

	/**
	 * 
	 * @return
	 */
	protected String createOrderBody() {
		// @formatter:off
		return "{\n"
				+ "  \"customer\":\"BUDZINSKI_SHAWN\",\n"
				+ "  \'model\":\"IPHONE14PRO\",\n"
				+ "  \"color\":\"Purple\",\n"
				+ " ]\n"
				+ "}";
		// @formatter:on
	}
}
