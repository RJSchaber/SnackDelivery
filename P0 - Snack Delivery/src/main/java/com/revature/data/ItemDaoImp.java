package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controller.CustomerController;
import com.revature.io.ObjectWriter;
import com.revature.models.Item;


public class ItemDaoImp implements ItemDao {
	private static ObjectWriter objectWriter = new ObjectWriter();
	private static String resourcePath = "src/main/resources/";
	private static Logger log = LogManager.getLogger(ItemDaoImp.class);

	private static List<Item> loadItems(){
		@SuppressWarnings("unchecked")
		List<Item> items =  (List<Item>) objectWriter.readObjectFromFile(resourcePath + "items.dat");
		
		if(items == null) {
			items = new ArrayList<Item>();
			log.trace("There is no available items list, creating one now.");
		}
		
		log.trace("Returning list of all items.");
		return items;
	}
	
	private static void saveItems(List<Item> items) {
		log.trace("Sending items list to objectWriter to be saved to file. Is items empty? " + items.isEmpty());
		objectWriter.writeObjectToFile(resourcePath + "items.dat", items);
	}
	
	@Override
	public List<Item> getItemByName(String itemName) {
		List<Item> items = loadItems();
		log.trace("Loading items from loadItems method and returning an arraylist of items that match item name:" + itemName + " Is items empty? " + items.isEmpty());
		items = items.stream().filter(item -> item.getItemName().equals(itemName)).collect(Collectors.toList());

		return items;
	}

	@Override
	public void addItem(Item i) throws Exception {
		List<Item> items = loadItems();
		log.trace("Loading items from loadItems method. Is items empty? " + items.isEmpty());

		if(i.getItemName() == null || i.getItemName().trim().isEmpty()) {
			log.trace("Item name is not valid: " + i.getItemName());
			throw new Exception("Item must have a name");
		}
		
		for(Item item : items) {
			if(item.getItemName().equals(i.getItemName()) && item.getBusinessName().equals(i.getBusinessName())) {
				log.trace("Already have this item in user inventory: " + i.getItemName());
				throw new Exception("You already have this item in your inventory");
			}
		}
		
		items.add(i);
		saveItems(items);
		log.trace("Successfully added: " + i.getItemName());
	}

	@Override
	public List<Item> getItemByMerchant(String businessName) {
		List<Item> items = loadItems();
		log.trace("Loading items from loadItems method. Returning items for business: " + businessName + "Is items empty? " + items.isEmpty());
		
		items = items.stream().filter(item -> item.getBusinessName().equals(businessName)).collect(Collectors.toList());
		return items;
	}

	@Override
	public void removeItem(Item i) {
		List<Item> items = loadItems();
		log.trace("Loading items from loadItems method. Is items empty? " + items.isEmpty());
		
		items.remove(i);
		saveItems(items);
		log.trace("Item: " + i.getItemName() + " removed from inventory.");
	}

	@Override
	public void updateItem(Item i) {
		List<Item> items = loadItems();
		log.trace("Loading items from loadItems method. Is items empty? " + items.isEmpty());
		
		items = items.stream().filter(item -> !item.getItemName().equals(i.getItemName())).collect(Collectors.toList());
		
		items.add(i);
		saveItems(items);
		log.trace("Item: " + i.getItemName() + " updated.");
	}

}
