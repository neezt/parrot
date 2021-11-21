package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.model.Order;
import com.example.model.Product;

public interface OrderService {

	Order getOrder(String orderId);
	
	Order addOrder(Order order);
	
	List<Product> getOrdersByProduct(Date dateBegin, Date dateFinish);
}
