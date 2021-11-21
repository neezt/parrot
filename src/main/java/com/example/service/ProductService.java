package com.example.service;

import java.util.List;

import com.example.model.Product;

public interface ProductService {

	Product addProduct(Product product);
	List<Product> getProductsByName(String productName);
	Product getProduct(String productId);
	
}
