package com.example.simplerestapis.models;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Item {
	
	private String itemName;
	
	private double itemMaxPrice;
	private Date date;
	
public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemMaxPrice() {
		return itemMaxPrice;
	}
	public void setItemMaxPrice(double itemMaxPrice) {
		this.itemMaxPrice = itemMaxPrice;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
