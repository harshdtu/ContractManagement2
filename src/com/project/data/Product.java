package com.project.data;

import java.util.ArrayList;

public class Product {

	int id;
	String productName;
	double price;
	int quantity;
	int category;
	ArrayList<Feature> feature;
	
	
	
	
	public Product(int id, String productName, double price, int quantity, int category, ArrayList<Feature> feature) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.feature = feature;
	}


	public Product() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String string) {
		this.productName = string;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
		this.category = category;
	}


	public ArrayList<Feature> getFeature() {
		return feature;
	}
	public void setFeature(ArrayList<Feature> feature) {
		this.feature = feature;
	}
}
