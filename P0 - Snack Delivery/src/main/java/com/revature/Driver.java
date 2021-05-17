package com.revature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controller.CustomerController;
import com.revature.controller.ItemController;
import com.revature.controller.OrderController;

import io.javalin.Javalin;

public class Driver {
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	
	
	public static void main(String[] args) {
		log.trace("Starting application");

		Javalin app = Javalin.create().start(8080);
		
		//Customer Controller
		app.get("/customers", CustomerController::getCustomers);
		app.put("/customers", CustomerController::register);
		app.post("/customers", CustomerController::login);
		app.delete("/customers", CustomerController::logout);
		app.get("/businesses", CustomerController::getBusinesses);

		//Item Controller
		app.post("/items", ItemController::addItem);
		app.get("/itemsbyname", ItemController::getItemsByName);
		app.get("/itemsbybusiness", ItemController::getItemsByBusiness);
		app.put("/items", ItemController::updateItem);
		app.delete("/items", ItemController::removeItem);
		
		//Order Controller
		app.get("/ordersbybusiness", OrderController::getOrdersByBusiness);
		app.get("/orders", OrderController::getOrdersByCustomer);
		app.post("/orders", OrderController::addOrder);

	}
}
