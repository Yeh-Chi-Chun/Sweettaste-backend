package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductBean;

@Repository
public class ProductDao {
	
	@Autowired private JdbcTemplate jdbcTemplate;
	
	
	private class ProductRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			ProductBean productBean = new ProductBean();
			
			
			productBean.setProductName(rs.getString("productName"));
			productBean.setProductPrice(rs.getString("productPrice"));
			productBean.setProductPic(rs.getString("productPic"));
			productBean.setReserve(rs.getString("reserve"));
			productBean.setNewList(rs.getString("newList"));
			productBean.setPopular(rs.getString("popular"));
			productBean.setFeatured(rs.getString("featured"));
			productBean.setIsCake(rs.getString("isCake"));
			productBean.setIsSweets(rs.getString("isSweets"));
			
			
			return productBean;
		}
	}
	
	public List<ProductBean> getData() {
		
		List<ProductBean> listData = new ArrayList<ProductBean>();
		listData = (List<ProductBean>)jdbcTemplate.query("SELECT * FROM product",
				new RowMapperResultSetExtractor(new ProductRowMapper()));
		
		
		return listData;
				
	}
	
	public String insertData(ProductBean data) {
			 jdbcTemplate.update("insert into product (productName,productPrice,productPic,reserve,newList,popular,featured,isCake,isSweets) values (?,?,?,?,?,?,?,?,?);", 
					data.getProductName(), data.getProductPrice(),data.getProductPic(),
					data.getReserve(),data.getNewList(),data.getPopular(),
					data.getFeatured(),data.getIsCake(),data.getIsSweets());
		
			 System.out.print("insert OK");
			 
		return "insert OK";
	}
	
	public String deleteProductByName(String productName) {  
		
		jdbcTemplate.update("delete from product where productName=?", productName);  
		
		System.out.print("delete ok");
		return "delete ok";
		}
	
	public String updateData(ProductBean data) {
		
		String SQLstr = "update product set productPrice=?,productPic=? ,reserve=?,newList=?,popular=?,featured=?,isCake=?,isSweets=? where productName=?";
		jdbcTemplate.update(SQLstr,data.getProductPrice(),data.getProductPic(),data.getReserve(),data.getNewList(),data.getPopular(),data.getFeatured(),data.getIsCake(),data.getIsSweets(),data.getProductName());
	
		 System.out.print("update OK");
		 
	return "update OK";
}
	

	
	
	

}
