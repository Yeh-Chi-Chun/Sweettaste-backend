package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductBean;
import com.example.demo.dto.UserBean;
import com.example.demo.dto.loginOBJ;

@Repository
public class UserDAO {
	
	@Autowired private JdbcTemplate jdbcTemplate;
	
	private class UserRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			UserBean userBean = new UserBean();
			
			
			userBean.setUserName(rs.getString("userName"));
			userBean.setPassword(rs.getString("password"));
			userBean.setEmail(rs.getString("email"));
			userBean.setAdmin(rs.getString("admin"));
			
			
			
			return userBean;
		}
	}
	
	public List<UserBean> getUserData() {
		
		List<UserBean> userListData = new ArrayList<UserBean>();
		userListData = (List<UserBean>)jdbcTemplate.query("SELECT * FROM user",
				new RowMapperResultSetExtractor(new UserRowMapper()));
		
		
		return userListData;			
	}
	
	public loginOBJ checkLogin(UserBean loginData)
	{
		List<UserBean> userListData = getUserData();
		loginOBJ loginObj = new loginOBJ();
		
		for(UserBean element : userListData) {
			System.out.print(element.getEmail());
			System.out.print(loginData.getEmail());
			if(element.getEmail().equals(loginData.getEmail()))
			{
				
				
				if(element.getPassword().equals(loginData.getPassword()))
				{
					loginObj.setAdmin(element.getAdmin());
					loginObj.setUserName(element.getUserName());
					loginObj.setStatus("1");
					loginObj.setMessage("登入成功");
				}
				else
				{
					loginObj.setStatus("0");
					loginObj.setMessage("密碼錯誤");
				}
				
			}
			else
			{
				loginObj.setStatus("0");
				loginObj.setMessage("找不到此帳號");
			}
	    }
		
		return loginObj;
	}
	
	
}
