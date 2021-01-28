package com.example.demo.dto;

import org.springframework.stereotype.Component;



@Component
public class dataBean {
	
	private  String data;
	private  String data2;
	
	
	@Override
	public String toString() {
		return "dataBean [data=" + data + ", data2=" + data2 + "]";
	}
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	
	

}
