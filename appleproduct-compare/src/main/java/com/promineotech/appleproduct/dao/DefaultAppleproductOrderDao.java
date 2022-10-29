package com.promineotech.appleproduct.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.promineotech.appleproduct.dao.DefaultAppleproductOrderDao.ModelResultSetExtractor.ColorResultSetExtractor;
import com.promineotech.appleproduct.dao.DefaultAppleproductOrderDao.ModelResultSetExtractor.ColorResultSetExtractor.SqlParams;
import com.promineotech.appleproduct.entity.Appleproduct;
import com.promineotech.appleproduct.entity.AppleproductModel;
import com.promineotech.appleproduct.entity.Color;
import com.promineotech.appleproduct.entity.Customer;
import com.promineotech.appleproduct.entity.Order;


@Component

public class DefaultAppleproductOrderDao implements AppleproductOrderDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Order saveOrder(Customer customer, Appleproduct appleproduct, Color color, BigDecimal price) {
		
		SqlParams params = generateInsertSql(customer, appleproduct, color, price); 
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(params.sql, params.source, keyHolder);
		
		Long orderPK = keyHolder.getKey().longValue();
		// @formatter:off
		return Order.builder()
				.orderPK(orderPK)
				.customer(customer)
				.model(appleproduct)
				.color(color)
				.build();
		// @formatter:on
	}
	
	/**
	 * 
	 * @param customer
	 * @param appleproduct
	 * @param color
	 * @param price
	 * @return
	 */
	private SqlParams generateInsertSql(Customer customer, Appleproduct appleproduct, Color color, BigDecimal price) {
		
		// @formatter:off
		String sql = ""
				+ "INSERT INTO orders ("
				+ "customer_fk, color_fk, model_fk, price"
				+ ") VALUES ("
				+ ":customer_fk, :color_fk, :model_fk, :price"
				+ ")";
		// @formatter:on
		
		SqlParams params = new SqlParams();
		
		params.sql = sql;
		params.source.addValue("customer_fk", customer.getCustomerPK());
		params.source.addValue("color_fk", color.getColorPK());
		params.source.addValue("model_fk", appleproduct.getModelPK());
		params.source.addValue("price", price);
				
				return params;
	}

	/**
	 * 
	 */
	@Override
	public Optional<Customer> fetchCustomer(String customerId) {
		String sql = ""
				+ "SELECT * "
				+ "FROM customers "
				+ "WHERE customer_id = :customer_id";
		
		Map<String, Object> params = new HashMap<>();
		params.put("customer_id", customerId);
		return Optional.ofNullable(
				jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
	}

	class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {

		@Override
		public Customer extractData(ResultSet rs)
				throws SQLException, DataAccessException {
			rs.next();
			// @formatter:off
			return Customer.builder()
					.customerId(rs.getString("customer_id"))
					.customerPK(rs.getLong("customer_pk"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.phone(rs.getString("phone"))
					.build();
			// @formatter:on
		}
		
	}
	
	/**
	 * 
	 */
	
	@Override
	public Optional<Appleproduct> fetchModel(AppleproductModel model, String color) {
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM models "
				+ "WHERE model_id = :model_id ";
			
		// @formatter:on
		Map<String, Object> params = new HashMap<>();
		params.put("model_id", model.toString());
		
		return Optional.ofNullable(
				jdbcTemplate.query(sql, params, new ModelResultSetExtractor()));
	}
	/**
	 * 
	 */
	@Override
	public Optional<Color> fetchColor(String colorId) {
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM colors "
				+ "WHERE color_id = :color_id";
		
		// @formatter:on
		
		Map<String, Object> params = new HashMap<>();
		params.put("color_id", colorId);
		
		return Optional.ofNullable(
				jdbcTemplate.query(sql, params, new ColorResultSetExtractor()));
	}
	
	class ModelResultSetExtractor implements ResultSetExtractor<Appleproduct> {
		@Override
		public Appleproduct extractData(ResultSet rs) throws SQLException {
			rs.next();
			// @formatter:off
			return Appleproduct.builder()
					.basePrice(rs.getBigDecimal("base_price"))
					.modelID(AppleproductModel.valueOf(rs.getString("model_id")))
					.modelPK(rs.getLong("model_pk"))
					.build();
			// @formatter:on
		}
		
		class ColorResultSetExtractor implements ResultSetExtractor<Color> {
			@Override
			public Color extractData(ResultSet rs) throws SQLException {
				rs.next();
				// @formatter:off
				return Color.builder()
						.color(rs.getString("color"))
						.colorId(rs.getString("color_id))"
					    .colorPK(rs.getLong("color_pk"))
						.build();
				// @formatter:on
			}
			
			class SqlParams {
				String sql;
				MapSqlParameterSource source = new MapSqlParameterSource();
			}
		}
	}

	@Override
	public boolean deleteOrder(String orderId) {
		String sql = """
				DELETE
				FROM orders
				WHERE order_id = :order_id
				""";
		Map<String, Object> params = Map.of("order_id", orderId);
		
		return jdbcTemplate.update(sql, params) == 1;
 	}

	@Override
	public boolean updateOrder(Order order) {
		String sql = """
				UPDATE orders SET 
				category = :category,
				WHERE order_pk = :order_pk
				""";
		
		Map<String, Object> params = Map.of("category", order.getCategory().toString(), "order_pk",
				order.getOrderPK());
		return jdbcTemplate.update(sql, params) == 1;
	}
}
	

	
	
	

