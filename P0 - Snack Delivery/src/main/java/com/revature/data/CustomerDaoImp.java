package com.revature.data;

import java.util.List;
import com.revature.models.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controller.CustomerController;
import com.revature.io.ObjectWriter;

public class CustomerDaoImp implements CustomerDao {
	private static ObjectWriter objectWriter = new ObjectWriter();
	private static String resourcePath = "src/main/resources/";
	private static Logger log = LogManager.getLogger(CustomerDaoImp.class);
	
	private static Map<String, Customer> loadCustomers(){
		@SuppressWarnings("unchecked")
		Map<String, Customer> customers = (Map<String, Customer>) objectWriter.readObjectFromFile(resourcePath + "customers.dat");
		
		if(customers == null) {
			customers = new HashMap<String, Customer>();
			log.trace("There is no available customers map, creating one now.");
		}
		
		log.trace("Returning map of all customers.");
		return customers;
	}
	
	private static void saveCustomers(Map<String, Customer> customers) {
		log.trace("Sending customers map to objectWriter to be saved to file. Is customers empty? " + customers.isEmpty());
		objectWriter.writeObjectToFile(resourcePath + "customers.dat", customers);
	}

	@Override
	public List<Customer> getCustomers() {
		Map<String, Customer> customers = loadCustomers();
		log.trace("Loading customers from loadCustomers method and returning an arraylist of the values of the customers map. Is customers empty? " + customers.isEmpty());
		return new ArrayList<Customer>(customers.values());
	}

	@Override
	public Customer getCustomerByName(String name) {
		Map<String, Customer> customers = loadCustomers();
		log.trace("Loading customers from loadCustomers method and returning a customer by name: " + name);
		return customers.get(name);
	}

	@Override
	public void addCustomer(Customer c) throws Exception {
		Map<String, Customer> customers = loadCustomers();
		log.trace("Loading customers from loadCustomers method");
		
		if(c.getCustomerName() == null || c.getCustomerName().trim().isEmpty()) {
			log.trace("Customer input does not include a name.");
			throw new Exception("Customer must have a name");
		}
		
		if(customers.get(c.getCustomerName()) != null) {
			log.trace("Customer already exists");
			throw new Exception("Customer already exists");
		}
		customers.put(c.getCustomerName(), c);
		saveCustomers(customers);
		
		log.trace("Saved " + c.getCustomerName() + " to map and then file.");
	}

	@Override
	public void updateCustomer(Customer c) {
		Map<String, Customer> customers = loadCustomers();
		customers.put(c.getCustomerName(), c);
		saveCustomers(customers);
		log.trace("Updated " + c.getCustomerName() + " to map and then file... but forgot to implement this method.  Sorry Rorr.");
	}

}
