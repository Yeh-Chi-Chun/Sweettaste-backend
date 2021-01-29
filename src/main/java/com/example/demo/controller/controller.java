package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderBean;
import com.example.demo.dto.OrderProductBean;
import com.example.demo.dto.ProductBean;
import com.example.demo.dto.UserBean;
import com.example.demo.dto.dataBean;
import com.example.demo.dto.loginOBJ;
import com.example.demo.repository.OrderDao;
import com.example.demo.repository.ProductDao;
import com.example.demo.repository.UserDAO;
import com.fasterxml.jackson.databind.util.JSONPObject;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class controller {
	
	@Autowired ProductDao productDao;
	@Autowired UserDAO userDao;
	@Autowired OrderDao orderDao;
	
	
	@GetMapping("/")
	@ResponseBody
	public Object home()
	{
		
		return userDao.getUserData();
	}
	
	@GetMapping("/data")
	@ResponseBody
	public dataBean homeData(@ModelAttribute dataBean databean) 
	{
		return databean;
	}
	
	@PostMapping("/insertProduct")
	@ResponseBody
	public String insertproduct(@RequestBody ProductBean data )
	{
		
		System.out.print(data.getProductName());
		
		return productDao.insertData(data);
	}
	
	@PostMapping("/delProduct")
	@ResponseBody
	public String delproduct(@RequestBody String productName)
	{
		
		System.out.print(productName);
		
		return productDao.deleteProductByName(productName);
	}
	
	@PostMapping("/updateProduct")
	@ResponseBody
	public String updateproduct(@RequestBody ProductBean data )
	{
		
		System.out.print(data.getProductName());
		
		return productDao.updateData(data);
	}
	
	
	@GetMapping("/product")
	@ResponseBody
	public List<ProductBean> productDao() 
	{
		return productDao.getData();
	}
	
	@PostMapping("/login")
	@ResponseBody
    public loginOBJ login(@RequestBody UserBean data){
		
        return userDao.checkLogin(data);
    }
	
	@PostMapping("/register")
	@ResponseBody
	public String register(@RequestBody UserBean data )
	{
		
		System.out.print(data.getUserName());
		
		return userDao.register(data);
	}
	
	@PostMapping("/insertOrder")
	@ResponseBody
	public String insertorder(@RequestBody OrderBean data )
	{
		
		System.out.println(data.getOid());
		
		return orderDao.insertOrder(data);
	}
	
	@PostMapping("/insertOrderProduct")
	@ResponseBody
	public String insertorderproduct(@RequestBody OrderProductBean data )
	{
		
		System.out.println(data.getOid());
		
		return orderDao.insertOrderProduct(data);
	}
	
	@GetMapping("/getOrderProduct")
	@ResponseBody
	public List<OrderProductBean> getOrderProduct() 
	{
		return orderDao.getOrderProductData();
	}
	
	@GetMapping("/getOrder")
	@ResponseBody
	public List<OrderBean> getOrder() 
	{
		return orderDao.getOrderData();
	}
	
	@PostMapping("/updateOrder")
	@ResponseBody
	public String updateOrder(@RequestBody OrderBean data )
	{
		
		System.out.println(data.getDelStatus());
		System.out.println(data.getOid());
		
		
		return orderDao.updateOrderData(data);
	}
	
	

}


