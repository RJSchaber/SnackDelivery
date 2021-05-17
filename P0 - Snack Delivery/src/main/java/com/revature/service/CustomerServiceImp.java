package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.data.CustomerDao;
import com.revature.data.CustomerDaoImp;
import com.revature.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerServiceImp implements CustomerService{
	private static Logger log = LogManager.getLogger(CustomerServiceImp.class);
	private CustomerDao cd = new CustomerDaoImp();
	
	
	@Override
	public Customer getCustomer(String name) {
		return cd.getCustomerByName(name);
	}
	
	@Override
	public boolean addCustomer(Customer c) {
		try {
			cd.addCustomer(c);
			return true;
		}catch (Exception e) {
			log.warn("Customer already exists " + c.getCustomerName());
			return false;
		}
	}
	
	@Override
	public List<Customer> getCustomers(){
		return cd.getCustomers();
	}
	
	@Override
	public List<Customer> getMerchants(){
		List<Customer> merchants = new ArrayList<Customer>();
		
		for(Customer c : cd.getCustomers()) {
			if(c.getIsMerchant()) {
				merchants.add(c);
				return merchants;
			}
		}
		return null;
	}
	
	@Override
	public List<Customer> getMerchantsNearMe(String address){
		List<Customer> merchants = new ArrayList<Customer>();
		
		for(Customer c : cd.getCustomers()) {
			if(c.getIsMerchant() && c.getCustomerAddress().equals(address)) {
				merchants.add(c);
				return merchants;
			}
		}
		return null;
	}

	@Override
	public Customer getCustomerByBusiness(String businessName) {
		Customer merchant = new Customer();
		
		for(Customer c : cd.getCustomers()) {
			if(c.getBusinessName().equals(businessName)) {
				return merchant;
			}
		}
		return null;
	}

}
