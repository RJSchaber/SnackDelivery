package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.data.ItemDao;
import com.revature.data.ItemDaoImp;
import com.revature.models.Item;


public class ItemServiceImp implements ItemService {
	private static Logger log = LogManager.getLogger(ItemServiceImp.class);
	private ItemDao itemdao = new ItemDaoImp();

	@Override
	public List<Item> getItemByName(String itemName) {

		return itemdao.getItemByName(itemName);
	}
	
	
	@Override
	public boolean addItem(Item i) {
		try {
			itemdao.addItem(i);
			return true;
		}catch (Exception e) {
			log.warn("Item cant be added " + i.getItemName());
			return false;
		}
	}
	
	
	@Override
	public List<Item> getItemByMerchant(String businessName) {
		return itemdao.getItemByMerchant(businessName);
	}


	@Override
	public void removeItem(Item i) {
		itemdao.removeItem(i);
	}


	@Override
	public void updateItem(Item i) {
		itemdao.updateItem(i);
	}
	
	
	/*
	 * @Override public Item getItemByNameMerchant(String itemName, String business)
	 * { for(Item i : Driver.items) { if(i.getItemName().equals(itemName) &&
	 * i.getBusinessName().equals(business)) { return i; } } return null; }
	 */
}
