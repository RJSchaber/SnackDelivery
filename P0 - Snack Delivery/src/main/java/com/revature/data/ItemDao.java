package com.revature.data;

import java.util.List;

import com.revature.models.Item;

public interface ItemDao {
	List<Item> getItemByName(String itemName);
	void addItem(Item i) throws Exception;
	List<Item> getItemByMerchant(String businessName);
	void removeItem(Item i);
	void updateItem(Item i);
}
