package com.example.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.dao.OrderDAO;
import com.example.exception.ParrotException;
import com.example.model.Order;
import com.example.model.Product;
import com.example.service.OrderService;
import com.example.service.ProductService;
import com.example.service.UserService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Order getOrder(String orderId) {
		return orderDAO.getOrder(orderId).get();
	}

	@Override
	public Order addOrder(Order order) {
		if(order.getCustomer().isEmpty()) {
			throw new ParrotException("Customer is empty");
		}
		
		if(order.getUser().getEmail().isEmpty()) {
			throw new ParrotException("User is empty");
		} else {
			order.setUser(userService.getUserByEmail(order.getUser().getEmail()));
		}
		
		if(order.getProducts().size() == 0) {
			throw new ParrotException("Your order need products");
		}
		
		List<Product> newProducts= new ArrayList<Product>();
		Double totalPrice=0.0;
		for (Product product : order.getProducts()) {
			if(product.getName().isEmpty()) {
				throw new ParrotException("Your product need a name");
			}
			
			if(product.getPrice().isNaN()) {
				throw new ParrotException("Your product " + product.getName() + " need a price");
			}
			
			if(product.getQuantity().isNaN()) {
				throw new ParrotException("Your product " + product.getName() + " need a quantity");
			}
			
			if(productService.getProductsByName(product.getName()).isEmpty()) {
				product.setName(product.getName().toUpperCase());
				newProducts.add(productService.addProduct(product));
			} else {
				Product newProduct =productService.getProductsByName(product.getName()).get(0);
				newProduct.setQuantity(product.getQuantity());
				newProduct.setPrice(product.getPrice());
				newProducts.add(newProduct);
			}
			totalPrice = totalPrice+(product.getPrice()*product.getQuantity());
		}
		order.setProducts(newProducts);
		order.setTotalPrice(totalPrice);
		order.setCreated(new Date());
		
		return orderDAO.addOrder(order);
	}

	@Override
	public List<Product> getOrdersByProduct(Date dateBegin, Date dateFinish) {
		List<Order> orders = orderDAO.getOrdersByFilter(dateBegin, dateFinish).get();
		List<Product> productResponse = new ArrayList<Product>();
		
		for (Order order : orders) {
			for (Product product : order.getProducts()) {
				Integer exist = existProduct(product, productResponse);
				if(exist == null) {
					productResponse.add(product);
				} else {
					productResponse.get(exist.intValue()).setPrice(productResponse.get(exist.intValue()).getPrice()+(product.getPrice()*product.getQuantity()));
					productResponse.get(exist.intValue()).setQuantity(productResponse.get(exist.intValue()).getQuantity()+product.getQuantity());
				}
			}
		}
		
		/**
		 * Order by price desc
		 */
		Collections.sort(productResponse, new Comparator<Product>() {
			  @Override
			  public int compare(Product u1, Product u2) {
			    return u2.getPrice().compareTo(u1.getPrice());
			  }
			});
		
		return productResponse;
	}
	
	public Integer existProduct(Product product, List<Product> products) {
		Integer resp=  null;
		
		for (int i = 0; i < products.size(); i++) {
			if(product.getName().equals(products.get(i).getName())) {
				resp = i;
			}
		}
		
		return resp;
	}

}
