package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "products")
@JsonPropertyOrder({"productId", "name","price","quantity"})
public class Product {

	@Id
	private String productId;
	private String name;
	private Double price;
	private Double quantity;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public Double compareTo(Product anotherProduct) {
		return ((Product) anotherProduct).getPrice() - this.price;
	}
	
	
}
