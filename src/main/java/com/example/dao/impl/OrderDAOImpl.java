package com.example.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.example.dao.OrderDAO;
import com.example.model.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {

	private final MongoOperations mongoOperations;

    @Autowired
    public OrderDAOImpl(MongoOperations mongoOperations) {
    	this.mongoOperations = mongoOperations;
    }
    
	@Override
	public Order addOrder(Order order) {
		this.mongoOperations.save(order);
		return getOrder(order.getOrderId()).get();
	}

	@Override
	public Optional<Order> getOrder(String orderId) {
		Order d = this.mongoOperations.findOne(new Query(Criteria.where("orderId").is(orderId)), Order.class);
		Optional<Order> order = Optional.ofNullable(d);
		return order;
	}

	@Override
	public Optional<List<Order>> getOrdersByFilter(Date dateBegin, Date dateFinish) {
		List<Order> d = this.mongoOperations.find(new Query(Criteria.where("created").gte(dateBegin).lt(dateFinish)), Order.class);
		Optional<List<Order>> orders = Optional.ofNullable(d);
		return orders;
	}

}
