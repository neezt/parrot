package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.Order;
import com.example.model.Product;
import com.example.service.OrderService;
import com.example.wrapper.FilterWrapper;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		return ResponseEntity.ok(this.orderService.addOrder(order));
	}
	
	@PostMapping("/report")
	public ResponseEntity<List<Product>> reportProduct(@RequestBody FilterWrapper filter) {
		return ResponseEntity.ok(this.orderService.getOrdersByProduct(filter.getDateBegin(), filter.getDateFinish()));
	};
}
