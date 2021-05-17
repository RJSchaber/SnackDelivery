package com.revature.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.io.ObjectWriter;
import com.revature.models.Customer;
import com.revature.models.Item;
import com.revature.models.Order;

public class OrderDaoImp implements OrderDao {
	private static ObjectWriter objectWriter = new ObjectWriter();
	private static String resourcePath = "src/main/resources/";
	private static Logger log = LogManager.getLogger(OrderDaoImp.class);
	
	private static List<Order> loadOrders(){
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) objectWriter.readObjectFromFile(resourcePath + "orders.dat");
		
		if(orders == null) {
			orders = new ArrayList<Order>();
			log.trace("There is no available orders list, creating one now.");
		}
		
		log.trace("Returning list of all orders.");
		return orders;
	}
	
	private static void saveOrders(List<Order> orders) {
		log.trace("Sending orders list to objectWriter to be saved to file. Is orders empty? " + orders.isEmpty());
		objectWriter.writeObjectToFile(resourcePath + "orders.dat", orders);
	}

	@Override
	public List<Order> getOrdersByCustomer(Customer c) {
		List<Order> orders = loadOrders();
		log.trace("Loading orders from loadOrders method and returning an arraylist of orders by customer. Is orders empty? " + orders.isEmpty());
		orders = orders.stream().filter(order -> order.getCustomerName().equals(c.getCustomerName())).collect(Collectors.toList());
		
		return orders;
	}

	@Override
	public List<Order> getOrderByBusiness(String businessName) {
		List<Order> orders = loadOrders();
		log.trace("Loading orders from loadOrders method and returning an arraylist of orders by business name. Is orders empty? " + orders.isEmpty());
		orders = orders.stream().filter(order -> order.getBusinessName().equals(businessName)).collect(Collectors.toList());
		return orders;
	}

	@Override
	public void addOrder(Order o) {
		List<Order> orders = loadOrders();
		log.trace("Loading orders from loadOrders method and then adding a new order for: " + o.getCustomerName());
		int id = orders.stream().mapToInt(order -> order.getOrderId()).max().orElse(0) + 1;
		double totalcost = 0;
		
		try {
		for(Item i: o.getItems()) {
			totalcost += (i.getCost()*i.getItemAmount());
			log.trace("Calculating total cost: " + totalcost);
			}
		}catch(Exception e) {
			log.trace(e.getMessage());
			for(StackTraceElement s : e.getStackTrace()) {
				log.warn(s);
			}
		}
		Date date = new Date();
		String dateFormatString = "EEE, MMM d, ''yy";
	    DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
	    String currentDate = dateFormat.format(date);
	    
	    o.setDateOrderPlaced(currentDate);
		o.setTotalCost(totalcost);
		o.setOrderId(id);
		orders.add(o);
		saveOrders(orders);
		log.trace("Order added successfully for: " + o.getCustomerName());
	}

}
