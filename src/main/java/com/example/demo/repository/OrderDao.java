package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.OrderBean;
import com.example.demo.dto.OrderProductBean;
import com.example.demo.dto.ProductBean;
import com.example.demo.dto.UserBean;

@Repository
public class OrderDao {
	
@Autowired private JdbcTemplate jdbcTemplate;
	
	private class OrderRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			OrderBean orderBean = new OrderBean();
			
			
			orderBean.setUserName(rs.getString("userName"));
			orderBean.setName(rs.getString("name"));
			orderBean.setPhoneNumber(rs.getString("phoneNumber"));
			orderBean.setEmail(rs.getString("email"));
			orderBean.setAddress(rs.getString("address"));
			orderBean.setAmount(rs.getString("amount"));
			orderBean.setDelStatus(rs.getString("delStatus"));
			orderBean.setOid(rs.getString("oid"));
			
			
			return orderBean;
		}
	}
	
	public List<OrderBean> getOrderData() {
		
		List<OrderBean> OrderListData = new ArrayList<OrderBean>();
		OrderListData = (List<OrderBean>)jdbcTemplate.query("SELECT * FROM `order`",
				new RowMapperResultSetExtractor(new OrderRowMapper()));
		
		
		return OrderListData;			
	}
	
	private class OrderProductRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			OrderProductBean orderProductBean = new OrderProductBean();
			
			
			orderProductBean.setOid(rs.getString("oid"));
			orderProductBean.setProductName(rs.getString("productName"));
			orderProductBean.setAmount(rs.getString("amount"));
		
			
			
			return orderProductBean;
		}
	}
	
	public List<OrderProductBean> getOrderProductData() {
		
		List<OrderProductBean> OrderProductListData = new ArrayList<OrderProductBean>();
		OrderProductListData = (List<OrderProductBean>)jdbcTemplate.query("SELECT * FROM orderproduct",
				new RowMapperResultSetExtractor(new OrderProductRowMapper()));
		
		
		return OrderProductListData;			
	}
	
	public String insertOrder(OrderBean data) {
		 jdbcTemplate.update("insert into `order` (`name`,email,userName,phoneNumber,address,amount,delStatus,oid) values (?,?,?,?,?,?,?,?);", 
				data.getName(), data.getEmail(),data.getUserName(),
				data.getPhoneNumber(),data.getAddress(),data.getAmount(),
				data.getDelStatus(),data.getOid());
	
		 System.out.print("insert OK");
		 
		 return "insert OK";
	}
	
	public String insertOrderProduct(OrderProductBean data) {
		 jdbcTemplate.update("insert into orderproduct (oid,productName,amount) values (?,?,?);", 
				data.getOid(), data.getProductName(),data.getAmount());
	
		 System.out.print("insert OK");
		 
		 return "insert OK";
	
		}
}
