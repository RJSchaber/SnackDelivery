package com.revature.service;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerService {

	Customer getCustomer(String name);

	boolean addCustomer(Customer c);

	List<Customer> getCustomers();

	List<Customer> getMerchants();

	List<Customer> getMerchantsNearMe(String address);
	
	Customer getCustomerByBusiness(String businessName);

}