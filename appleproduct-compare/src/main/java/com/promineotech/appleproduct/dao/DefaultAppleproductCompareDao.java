package com.promineotech.appleproduct.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultAppleproductCompareDao implements AppleproductCompareDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Appleproduct> fetchAppleproduct(AppleproductModel model, String color) {
		log.debug("DAO: model={}, color={}", model, color);
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM models "
				+ "WHERE model_id = :model_id AND color_id = :color_id";
		// @formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("model_id", model.toString());
		params.put("color_id", color);
		
		
		return jdbcTemplate.query(sql, params,
				new RowMapper<>() {

					@Override
					public Appleproduct mapRow(ResultSet rs, int rowNum) throws SQLException {
						// @formatter:off
						return Appleproduct.builder()
								.basePrice(new BigDecimal(rs.getString("base_price")))
								.modelID(AppleproductModel.valueOf(rs.getString("model_id")))
								.modelPK(rs.getLong("model_pk"))
								.color(rs.getString("color_id"))
								.cpuGen(rs.getInt("cpu_gen"))
								.storageAvail(rs.getInt("storage_avail"))
								.build();
						// @formatter:on
						
					}});
	}

}
