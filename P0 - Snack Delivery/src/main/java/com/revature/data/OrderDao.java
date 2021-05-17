package com.revature.data;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Order;

public interface OrderDao {
	List<Order> getOrdersByCustomer(Customer c);
	List<Order> getOrderByBusiness(String businessName);
	void addOrder(Order o);
}
