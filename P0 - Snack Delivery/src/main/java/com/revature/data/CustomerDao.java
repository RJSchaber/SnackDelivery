package com.revature.data;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao {
	List<Customer> getCustomers();
	Customer getCustomerByName(String name);
	void addCustomer(Customer c) throws Exception;
	void updateCustomer(Customer c);
}
