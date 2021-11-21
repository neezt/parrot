package com.example.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.example.dao.ProductDAO;
import com.example.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	private final MongoOperations mongoOperations;

    @Autowired
    public ProductDAOImpl(MongoOperations mongoOperations) {
    	this.mongoOperations = mongoOperations;
    }
    
	@Override
	public Product addProduct(Product product) {
		this.mongoOperations.save(product);
		return this.getProduct(product.getProductId()).get();
	}

	@Override
	public Optional<Product> getProduct(String productId) {
		Product d = this.mongoOperations.findOne(new Query(Criteria.where("productId").is(productId)), Product.class);
		Optional<Product> product = Optional.ofNullable(d);
		return product;
	}

	@Override
	public Optional<List<Product>> getProductByName(String nameProduct) {
		List<Product> d = this.mongoOperations.find(new Query(Criteria.where("name").regex(nameProduct.toUpperCase())), Product.class);
		Optional<List<Product>> product = Optional.ofNullable(d);
		return product;
	}

}
