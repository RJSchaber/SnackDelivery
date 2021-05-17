package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Driver;
import com.revature.data.ItemDaoImp;
import com.revature.data.OrderDao;
import com.revature.data.OrderDaoImp;
import com.revature.models.Customer;
import com.revature.models.Order;

public class OrderServiceImp implements OrderService{
	private OrderDao od = new OrderDaoImp();
	private static Logger log = LogManager.getLogger(OrderServiceImp.class);
	
	@Override
	public void addOrder(Order o) {
		
		log.trace("Service Layer: Order for " + o.getCustomerName() + " being sent to order dao to be added to orders");
		od.addOrder(o);
	}
	
	@Override
	public List<Order> getOrder(Customer customer) {
		
		log.trace("Service Layer: Getting orders for " + customer.getCustomerName() + " from orderdao");
		return od.getOrdersByCustomer(customer);
	} 
	
	@Override
	public List<Order> getOrder(String businessName){
		
		
		return od.getOrderByBusiness(businessName);
	}

	/*
	 * @Override public Order getOrder(int orderId) { for(Order o : Driver.orders) {
	 * if(o.getOrderId() == orderId) { return o; } } return null; }
	 */
	
}
