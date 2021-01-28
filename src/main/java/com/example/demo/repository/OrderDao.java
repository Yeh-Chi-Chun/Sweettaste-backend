package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.example.demo.dto.OrderBean;
import com.example.demo.dto.UserBean;

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
	
	public List<OrderBean> getUserData() {
		
		List<OrderBean> OrderListData = new ArrayList<OrderBean>();
		OrderListData = (List<OrderBean>)jdbcTemplate.query("SELECT * FROM user",
				new RowMapperResultSetExtractor(new OrderRowMapper()));
		
		
		return OrderListData;			
	}

}
