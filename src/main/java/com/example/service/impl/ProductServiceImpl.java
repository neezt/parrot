package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ProductDAO;
import com.example.model.Product;
import com.example.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public Product addProduct(Product product) {
		return this.productDAO.addProduct(product);
	}

	@Override
	public List<Product> getProductsByName(String productName) {
		Optional<List<Product>> products =this.productDAO.getProductByName(productName);
		return products.get();
	}

	@Override
	public Product getProduct(String productId) {
		Optional<Product> p =this.productDAO.getProduct(productId); 
		return p.get();
	}

}
