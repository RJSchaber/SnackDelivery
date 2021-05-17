package com.revature.service;

import java.util.List;

import com.revature.models.Item;

public interface ItemService {

	List<Item> getItemByName(String itemName);
	boolean addItem(Item i);
	List<Item> getItemByMerchant(String businessName);
	void removeItem(Item i);
	void updateItem(Item i);
	/* Item getItemByNameMerchant(String itemName, String business); */

}