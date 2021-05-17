package com.revature.models;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{
	
	private static final long serialVersionUID = 234525671345L;
	
	private int orderId;
	private List<Item> items;
	private double totalCost;
	private String businessName;
	private String customerName;
	private String customerAddress;
	private String dateOrderPlaced;
	
	public Order() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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

	public String getDateOrderPlaced() {
		return dateOrderPlaced;
	}

	public void setDateOrderPlaced(String dateOrderPlaced) {
		this.dateOrderPlaced = dateOrderPlaced;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
		result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((dateOrderPlaced == null) ? 0 : dateOrderPlaced.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + orderId;
		long temp;
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (businessName == null) {
			if (other.businessName != null)
				return false;
		} else if (!businessName.equals(other.businessName))
			return false;
		if (customerAddress == null) {
			if (other.customerAddress != null)
				return false;
		} else if (!customerAddress.equals(other.customerAddress))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dateOrderPlaced == null) {
			if (other.dateOrderPlaced != null)
				return false;
		} else if (!dateOrderPlaced.equals(other.dateOrderPlaced))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (orderId != other.orderId)
			return false;
		if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", items=" + items + ", totalCost=" + totalCost + ", businessName="
				+ businessName + ", customerName=" + customerName + ", customerAddress=" + customerAddress
				+ ", dateOrderPlaced=" + dateOrderPlaced + "]";
	}
	
	
	

}