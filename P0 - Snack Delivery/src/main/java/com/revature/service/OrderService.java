package com.revature.service;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Order;

public interface OrderService {

	void addOrder(Order o);
	
	/* Order getOrder(int orderId); */

	List<Order> getOrder(Customer customer);

	List<Order> getOrder(String businessName);

}