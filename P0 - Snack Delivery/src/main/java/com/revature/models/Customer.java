package com.revature.models;

import java.io.Serializable;
import java.util.List;

import com.revature.service.CustomerService;
import com.revature.service.CustomerServiceImp;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 234525671345L;
	
	private String customerName;
	private String customerAddress;
	private String businessName;
	private Boolean isMerchant;
	
	public Customer() {
		super();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	public Boolean getIsMerchant() {
		return isMerchant;
	}

	public void setIsMerchant(Boolean isMerchant) {
		this.isMerchant = isMerchant;
	}
	
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	
}
