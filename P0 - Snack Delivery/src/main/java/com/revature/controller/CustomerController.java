package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Customer;
import com.revature.service.CustomerService;
import com.revature.service.CustomerServiceImp;

import io.javalin.http.Context;

public class CustomerController {

	private static CustomerService cs = new CustomerServiceImp();
	private static Logger log = LogManager.getLogger(CustomerController.class);

	public static void getCustomers(Context ctx) {

		 Customer c = ctx.sessionAttribute("Customer");
		 
		 if(c == null || !c.getIsMerchant()) 
		 { 
			 ctx.status(403);
			 log.trace(ctx.status(403) + " \n Customer not logged in or not a merchant");
			 return; 
		 }

		log.trace("Get customers");
		log.debug(ctx.method() + " called for " + ctx.fullUrl());

		ctx.json(cs.getCustomers());
	}

	public static void register(Context ctx) {

		Customer c = ctx.bodyAsClass(Customer.class);
		boolean added = cs.addCustomer(c);
		if (added) {
			ctx.json(c);
			log.trace("Registered " + c.getCustomerName());
		} else {
			ctx.status(409);
			log.trace(ctx.status(409) + " \n " + c.getCustomerName() + " already exists");
		}
		
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
	}

	public static void login(Context ctx) {
		// We can get session information from the Context to use it elsewhere.
		if (ctx.sessionAttribute("Customer") != null) {
			ctx.status(204);
			log.trace(ctx.status(204) + " \n Customer logged in already");
			return;
		}

		String name = ctx.formParam("customerName");
		Customer c = cs.getCustomer(name);
		log.debug("Attempting to login " + name);

		if (c == null) {
			ctx.status(401);
			log.trace(ctx.status(401) + " \n" + name + " does not exist");
		} else {
			ctx.sessionAttribute("Customer", c);
			log.trace( c.getCustomerName() + "Logged in successfully");
			ctx.json(c);
		}
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
	}

	public static void logout(Context ctx) {
		Customer c = ctx.sessionAttribute("Customer");
		 
		 if(c == null) 
		 { 
			 ctx.status(403);
			 log.trace(ctx.status(403) + "\n Logout attempt denied. Nobody is logged in");
			 return; 
		 }
		 
		 log.trace(c.getCustomerName() + " Logged Out");
		 log.debug(ctx.method() + " called for " + ctx.fullUrl());
		 ctx.req.getSession().invalidate();
	}
	
	public static void getBusinesses(Context ctx) {
		Customer c = ctx.sessionAttribute("Customer");
		 
		 if(c == null) 
		 { 
			 ctx.status(403); 
			 log.trace(ctx.status(403) + "\n Get businesses denied. Nobody is logged in");
			 return; 
		 }
		 
		 log.trace("Get Local Businesses");
		 log.debug(ctx.method() + " called for " + ctx.fullUrl());
		 
		 ctx.json(cs.getMerchantsNearMe(c.getCustomerAddress()));
	}

}
