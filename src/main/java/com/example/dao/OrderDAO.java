package com.example.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.model.Order;

public interface OrderDAO {

	Order addOrder(Order order);
	Optional<Order> getOrder(String orderId);
	Optional<List<Order>> getOrdersByFilter(Date dateBegin, Date dateFinish);
}
