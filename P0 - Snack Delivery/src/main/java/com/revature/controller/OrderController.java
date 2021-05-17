package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Customer;
import com.revature.models.Order;
import com.revature.service.OrderService;
import com.revature.service.OrderServiceImp;

import io.javalin.http.Context;

public class OrderController {
	private static OrderService os = new OrderServiceImp();
	private static Logger log = LogManager.getLogger(OrderController.class);
	
	public static void getOrdersByCustomer(Context ctx){
		Customer c = ctx.sessionAttribute("Customer");
		
		if(c == null) {
			ctx.status(403);
			log.trace(ctx.status(403) + " \n Customer not logged in");
			return;
		}
		
		log.trace("Get orders by customer");
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
		ctx.json(os.getOrder(c));
	}
	
	public static void getOrdersByBusiness(Context ctx){
		Customer c = ctx.sessionAttribute("Customer");
		
		if(c == null || !c.getIsMerchant()) {
			ctx.status(403);
			log.trace(ctx.status(403) + " \n Customer not logged in or not a merchant");
			return;
		}
		
		log.trace("Get orders by business name: " + c.getBusinessName());
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
		ctx.json(os.getOrder(c.getBusinessName()));
	}
	
	public static void addOrder(Context ctx) {
		Customer c = ctx.sessionAttribute("Customer");
		
		if(c == null) {
			ctx.status(403);
			log.trace(ctx.status(403) + " \n Customer not logged in");
			return;
		}
		
		log.trace("Add order for customer: " + c.getCustomerName());
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
		Order o = ctx.bodyAsClass(Order.class);
		
		o.setCustomerName(c.getCustomerName());
		o.setCustomerAddress(c.getCustomerAddress());
		os.addOrder(o);
		ctx.json(o);
	}
	
}
