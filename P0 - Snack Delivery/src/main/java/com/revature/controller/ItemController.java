package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Customer;
import com.revature.models.Item;
import com.revature.service.ItemService;
import com.revature.service.ItemServiceImp;

import io.javalin.http.Context;

public class ItemController {
	private static ItemService is = new ItemServiceImp();
	private static Logger log = LogManager.getLogger(ItemController.class);
	
	
	public static void getItemsByBusiness(Context ctx){
		Customer c = ctx.sessionAttribute("Customer");
		
		if(c == null) {
			ctx.status(403);
			log.trace(ctx.status(403) + " \n Customer not logged in");
			return;
		}
		
		String businessName = ctx.formParam("businessName");
		
		log.debug(businessName);
		
		
		log.trace("Get items by business");
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
		
		ctx.json(is.getItemByMerchant(businessName));
	}
	
	public static void getItemsByName(Context ctx){
		Customer c = ctx.sessionAttribute("Customer");
		
		if(c == null) {
			ctx.status(403);
			log.trace(ctx.status(403) + " \n Customer not logged in");
			return;
		}
		
		String itemName = ctx.formParam("itemName");
		
		log.trace("Get items by name");
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
		
		ctx.json(is.getItemByName(itemName));
	}
	
	public static void addItem(Context ctx) {
		Customer c = ctx.sessionAttribute("Customer");
		Item i = ctx.bodyAsClass(Item.class);
		
		if(c == null || !c.getIsMerchant() || !c.getBusinessName().equals(i.getBusinessName())) {
			ctx.status(403);
			log.trace(ctx.status(403) + " \n Customer not logged in or trying to add an item to another business");
			return;
		}
		
		boolean added = is.addItem(i);
		if(added) {
			ctx.json(i);
			log.trace("Added new item: " + i.getItemName() + " to business: " + i.getBusinessName());
		}else {
			ctx.status(409);
			log.trace(ctx.status(409) + " \n Item already exists");
		}
		
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
		
	}
	
	public static void updateItem(Context ctx) {
		Customer c = ctx.sessionAttribute("Customer");
		Item i = ctx.bodyAsClass(Item.class);
		
		if(c == null || !c.getBusinessName().equals(i.getBusinessName())) {
			ctx.status(403);
			log.trace(ctx.status(403) + " \n Customer not logged in");
			return;
		}
		
		log.trace("Update Item " + i.getItemName());
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
		
		is.updateItem(i);
		ctx.json(i);
	}
	
	public static void removeItem(Context ctx) {
		Customer c = ctx.sessionAttribute("Customer");
		
		Item i = ctx.bodyAsClass(Item.class);
		
		if(c == null || !c.getIsMerchant() || !c.getBusinessName().equals(i.getBusinessName())) {
			ctx.status(403);
			log.trace(ctx.status(403) + " \n Customer not logged in");
			return;
		}
		
		is.removeItem(i);
		ctx.json(i);
		
		log.trace("Remove Item " + i.getItemName());
		log.debug(ctx.method() + " called for " + ctx.fullUrl());
		
	}
}
