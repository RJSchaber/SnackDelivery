/*
 * package com.revature.menu;
 * 
 * import java.text.SimpleDateFormat; import java.util.ArrayList; import
 * java.util.Collections; import java.util.Date; import java.util.List; import
 * java.util.Scanner;
 * 
 * import com.revature.Driver; import com.revature.models.Customer; import
 * com.revature.models.Item; import com.revature.service.CustomerService; import
 * com.revature.service.CustomerServiceImp; import
 * com.revature.service.ItemService; import com.revature.service.ItemServiceImp;
 * import com.revature.models.Order; import com.revature.service.OrderService;
 * import com.revature.service.OrderServiceImp;
 * 
 * public class Menus {
 * 
 * private Scanner scan = new Scanner(System.in); private CustomerService cs =
 * new CustomerServiceImp(); private OrderService os = new OrderServiceImp();
 * private ItemService is = new ItemServiceImp();
 * 
 * public int startMenu() {
 * 
 * System.out.println("Please select an option:");
 * System.out.println("\t1. Login"); System.out.println("\t2. Register");
 * System.out.println("\t3. Quit"); return Integer.parseInt(scan.nextLine()); }
 * 
 * public void close() { scan.close(); }
 * 
 * public void login() { System.out.println("Please enter your name: ");
 * Customer c = cs.getCustomer(scan.nextLine()); if(c == null) { return; }
 * loggedIn(c);
 * 
 * }
 * 
 * public void loggedIn(Customer c) { System.out.println("Hello there " +
 * c.getCustomerName() + "! "); loggedInLoop: while(true) {
 * System.out.println("What would you like to do?");
 * System.out.println("\t1. Order by current town");
 * System.out.println("\t2. Order by item");
 * System.out.println("\t3. See order history"); if(c.getIsMerchant()) {
 * System.out.println("\t4. Add to inventory.");
 * System.out.println("\t5. See sales history"); }
 * System.out.println("\t0. Quit"); switch(Integer.parseInt(scan.nextLine())) {
 * case 0: break loggedInLoop; case 1: MerchantsNearMe(c); break; case 2:
 * getItemByName(c); break; case 3: GetOrdersByCustomer(c); break; case 4:
 * addItem(c); break; case 5: GetOrdersByBusiness(c); break; } } }
 * 
 * public void register() {
 * 
 * System.out.println("Please enter your name: "); String name =
 * scan.nextLine(); String business = null;
 * 
 * Customer c = cs.getCustomer(name);
 * 
 * while(c != null & name.length() > 0) {
 * System.out.println("Name already exists.  Please enter a new name."); name =
 * scan.nextLine(); c = cs.getCustomer(name); }
 * 
 * System.out.println("Whats your town? "); String address = scan.nextLine();
 * 
 * while(address == null || address.trim().isEmpty()) {
 * System.out.println("Invalid Address, please try again: "); address =
 * scan.nextLine(); }
 * 
 * System.out.println("Are you a merchant? true or false"); boolean isMerchant =
 * Boolean.parseBoolean(scan.nextLine()); System.out.println(isMerchant);
 * 
 * if(isMerchant) { System.out.println("What is your business' name?"); business
 * = scan.nextLine();
 * 
 * while(business == null || business.trim().isEmpty()) {
 * System.out.println("Invalid business name, please try again: "); business =
 * scan.nextLine(); }
 * 
 * } c = new Customer(); c.setCustomerName(name); c.setCustomerAddress(address);
 * c.setIsMerchant(isMerchant); c.setBusinessName(business); cs.addCustomer(c);
 * System.out.println(c.getCustomerName() + " created");
 * 
 * }
 * 
 * public void addItem(Customer c) {
 * 
 * String businessName = c.getBusinessName(); int itemCost; int itemAmount; int
 * increaseInv;
 * 
 * System.out.println("Please enter your item name: "); String itemName =
 * scan.nextLine();
 * 
 * Item item = is.getItemByNameMerchant(itemName, businessName);
 * 
 * //if statement if(item != null) { System.out.
 * println("You already have this item in your inventory.  Increase item amount by how much? "
 * ); increaseInv = Integer.parseInt(scan.nextLine()); if(increaseInv > 0) {
 * itemAmount = item.getItemAmount() + increaseInv; //going to have to update
 * the item here. return; } else return; }
 * 
 * Item i = new Item(); i.setItemName(itemName);
 * 
 * System.out.println("Please enter your item cost: "); itemCost =
 * Integer.parseInt(scan.nextLine()); i.setCost(itemCost);
 * 
 * System.out.println("Please set item amount: "); itemAmount =
 * Integer.parseInt(scan.nextLine()); i.setItemAmount(itemAmount);
 * 
 * i.setBusinessName(businessName); is.addItem(i);
 * System.out.println(i.toString() + " created");
 * 
 * }
 * 
 * public void getItemByName(Customer c) {
 * System.out.println("Please enter your item name: "); List<Item> tempItems =
 * new ArrayList<Item>(is.getItemByName(scan.nextLine()));
 * 
 * for(int i = 1; i <= tempItems.size(); i++) { System.out.println(i + ". " +
 * tempItems.get(i-1).getBusinessName() + " " +
 * cs.getCustomerByBusiness(tempItems.get(i-1).getBusinessName()).
 * getCustomerAddress()); }
 * 
 * System.out.println("Please select a merchant: "); int merchSelect =
 * Integer.parseInt(scan.nextLine());
 * 
 * String merchBusiness = tempItems.get(merchSelect - 1).getBusinessName();
 * 
 * PlaceOrder(c, merchBusiness); }
 * 
 * private void MerchantsNearMe(Customer c) {
 * 
 * List<Customer> nearbyMerchants = new
 * ArrayList<Customer>(cs.getMerchantsNearMe(c.getCustomerAddress()));
 * 
 * for(int i = 1; i <= nearbyMerchants.size(); i++) { System.out.println(i +
 * ". " + nearbyMerchants.get(i-1).getBusinessName()); }
 * 
 * System.out.println("Please select a merchant: "); int merchSelect =
 * Integer.parseInt(scan.nextLine());
 * 
 * String merchBusiness = nearbyMerchants.get(merchSelect -
 * 1).getBusinessName();
 * 
 * PlaceOrder(c, merchBusiness); }
 * 
 * private void GetOrdersByBusiness(Customer c) {
 * 
 * List<Order> businessOrders = new
 * ArrayList<Order>(os.getOrder(c.getBusinessName()));
 * 
 * for(Order o : businessOrders) { o.toString(); } }
 * 
 * private void GetOrdersByCustomer(Customer c) {
 * 
 * List<Order> customerOrders = new ArrayList<Order>(os.getOrder(c));
 * 
 * for(Order o : customerOrders) { o.toString(); } }
 * 
 * private void PlaceOrder(Customer c, String businessName) {
 * 
 * List<Item> merchItems = new
 * ArrayList<Item>(is.getItemByMerchant(businessName)); Order custOrder = new
 * Order(); custOrder.setBusinessName(businessName);
 * custOrder.setCustomerAddress(c.getCustomerAddress());
 * custOrder.setCustomerName(c.getCustomerName());
 * custOrder.setDateOrderPlaced(new SimpleDateFormat("dd-MM-yyyy").format(new
 * Date())); custOrder.setTotalCost(0); List<Item> itemsToBuy = new
 * ArrayList<Item>();
 * 
 * //finding and setting the OrderID based on the max value of the current ID +
 * 1 List<Integer> tempList = new ArrayList<Integer>(); for(Order o :
 * Driver.orders) { tempList.add(o.getOrderId()); }
 * 
 * if(tempList.size() < 1) { custOrder.setOrderId(0); }else {
 * custOrder.setOrderId(Collections.max(tempList)+1); }
 * 
 * for(int i = 1; i <= merchItems.size(); i++) { System.out.println(i + ". " +
 * merchItems.get(i-1).toString()); }
 * 
 * orderLoop: while(true) { itemsToBuy.toString();
 * System.out.println("Add an item to your order: "); int itemNumber =
 * Integer.parseInt(scan.nextLine());
 * 
 * if(itemNumber == 0) { break orderLoop; } else { Item custItem = new Item();
 * custItem = merchItems.get(itemNumber);
 * 
 * System.out.println("How many would you like? "); int itemAmount =
 * Integer.parseInt(scan.nextLine());
 * 
 * custItem.setItemAmount(itemAmount);
 * custItem.setBusinessName(c.getCustomerName()); itemsToBuy.add(custItem);
 * 
 * is.addItem(custItem); } }
 * 
 * custOrder.setItems(itemsToBuy); os.addOrder(custOrder); }
 * 
 * 
 * }
 */