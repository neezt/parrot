package com.example.dao;

import java.util.List;
import java.util.Optional;
import com.example.model.Product;

public interface ProductDAO {

	Product addProduct(Product product);
	Optional<Product> getProduct(String productId);
	Optional<List<Product>> getProductByName(String nameProduct);
	
}
