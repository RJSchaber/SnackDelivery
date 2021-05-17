package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import com.revature.data.CustomerDaoImp;
import com.revature.models.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	CustomerServiceImp customerService;

	@Mock
	CustomerDaoImp cdaoMock;
	
	  @Test 
	  public void testGetCustomer() {
		  
		  Customer customer = new Customer();
		  customer.setCustomerName("Richard");
		  customer.setBusinessName("Richs Shrimp");
		  customer.setCustomerAddress("Avenel");
		  customer.setIsMerchant(true);
		  
	  customerService = new CustomerServiceImp();
	  ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
	  Whitebox.setInternalState(customerService, "cd", cdaoMock);
	  
	  customerService.getCustomer(customer.getCustomerName());
	  
	  verify(cdaoMock).getCustomerByName(captor.capture());
	  
	  assertEquals(customer.getCustomerName(), captor.getValue()); 
	  
	  }
	
	  @Test public void testGetCustomerByBusiness() {
		  
	  }
	  
	  @Test public void testGetCustomers() {
		  
	  }
	  
//	  @Test public void testGetMerchants() {
//		  Customer customer = new Customer();
//		  customer.setCustomerName("Richard");
//		  customer.setBusinessName("Richs Shrimp");
//		  customer.setCustomerAddress("Avenel");
//		  customer.setIsMerchant(true);
//		  
//		  Customer nonMerchant = new Customer();
//		  nonMerchant.setCustomerName("John");
//		  nonMerchant.setBusinessName(null);
//		  nonMerchant.setCustomerAddress("Trenton");
//		  nonMerchant.setIsMerchant(false);
//		  
//		  List<Customer> merchants = new ArrayList<Customer>();
//		  merchants.add(customer);
//		  
//		  List<Customer> nonMerchants = new ArrayList<Customer>();
//		  merchants.add(customer);
//		  nonMerchants.add(nonMerchant);
//		  
//		  customerService = new CustomerServiceImp();
//		  Whitebox.setInternalState(customerService, "cd", cdaoMock);
//		  
//		  when(cdaoMock.getCustomers()).thenReturn(nonMerchants);
//		  List<Customer> receivedMerch = customerService.getMerchants();
//		  assertEquals(merchants, receivedMerch);
//	  }
	  
	  @Test 
	  public void testAddCustomer() throws Exception {
		  Customer customer = new Customer();
		  customer.setCustomerName("Richard");
		  customer.setBusinessName("Richs Shrimp");
		  customer.setCustomerAddress("Avenel");
		  customer.setIsMerchant(true);
		  
		  customerService = new CustomerServiceImp();
		  Whitebox.setInternalState(customerService, "cd", cdaoMock);
		  ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
		  
		  customerService.addCustomer(customer);
		  verify(cdaoMock).addCustomer(captor.capture());
		  assertEquals(customer, captor.getValue());
	  }
	  
	  @Test public void testMerchantsNearMe() {
		  Customer customer = new Customer();
		  customer.setCustomerName("Richard");
		  customer.setBusinessName("Richs Shrimp");
		  customer.setCustomerAddress("Avenel");
		  customer.setIsMerchant(true);
		  
		  Customer notNearMe = new Customer();
		  notNearMe.setCustomerName("John");
		  notNearMe.setBusinessName("Stinkys house of cards idk");
		  notNearMe.setCustomerAddress("Trenton");
		  notNearMe.setIsMerchant(true);
		  
		  List<Customer> merchants = new ArrayList<Customer>();
		  merchants.add(customer);
		  merchants.add(notNearMe);
		  
		  List<Customer> merchs = new ArrayList<Customer>();
		  merchs.add(customer);
		  
		  customerService = new CustomerServiceImp();
		  
		  Whitebox.setInternalState(customerService, "cd", cdaoMock);
		  when(customerService.getMerchantsNearMe(customer.getCustomerAddress())).thenReturn(merchants);
		  List<Customer> receivedMerch = customerService.getMerchantsNearMe(customer.getCustomerAddress());
		  
		  assertNotEquals(merchants, receivedMerch);
		  assertEquals(merchs, receivedMerch);
	  }
}
