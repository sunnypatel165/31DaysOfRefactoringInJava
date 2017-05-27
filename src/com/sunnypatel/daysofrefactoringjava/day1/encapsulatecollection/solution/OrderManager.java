package com.sunnypatel.daysofrefactoringjava.day1.encapsulatecollection.solution;

import java.util.ArrayList;
import java.util.List;
/*
 * In certain scenarios it is beneficial to not expose a full collection to consumers of a class. 
Some of these circumstances is when there is additional logic associated with adding/removing items from a collection. 
Because of this reason, it is a good idea to only expose the collection as something you can iterate over without modifying the collection.
 */
public class OrderManager {
	private List<Order> orderList;
	private int ordersTotal =0;
	
	public OrderManager(){
		orderList = new ArrayList<>();
		
	}
	public void addOrder(Order order){
		orderList.add(order);
		ordersTotal += order.getValue();
	}
	public void removeOrder(Order order){
		if(orderList.contains(order)){
			ordersTotal -= order.getValue();
			orderList.remove(order);
		}
	}
	public int getOrdersTotal(){
		return ordersTotal;
	}
}
