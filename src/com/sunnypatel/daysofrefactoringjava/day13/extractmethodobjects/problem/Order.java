package com.sunnypatel.daysofrefactoringjava.day13.extractmethodobjects.problem;

import java.util.ArrayList;
import java.util.List;
/*
 * When trying to
apply an Extract Method refactoring, and multiple methods are needing to be introduced, it sometimes
gets ugly because of multiple local variables that are being used within a method. 
Because of this reason, it
is better to introduce an Extract Method Object refactoring and to segregate the logic required to perform
the task.
*/
public class Order {
	private List<OrderLineItem> orderLineItems = new ArrayList<>();
	private List<Double> discounts = new ArrayList<>();
	private double tax;
	private double subtotal = 0;
	
	public double calculate(){
		orderLineItems.forEach(orderLineItem -> {
			subtotal += orderLineItem.getPrice();
		});
		discounts.forEach(discount -> {
			subtotal -= discount;
		});
		tax = subtotal * tax;
		
		return subtotal+tax;
	}
	public List<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}
	public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}
	public List<Double> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<Double> discounts) {
		this.discounts = discounts;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	

}
