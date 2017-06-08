package com.sunnypatel.daysofrefactoringjava.day13.extractmethodobjects.solution;

import java.util.ArrayList;
import java.util.List;

public class OrderTotalCalculator {
	private List<OrderLineItem> orderLineItems = new ArrayList<>();
	private List<Double> discounts = new ArrayList<>();
	private double tax;
	private double subtotal = 0;
	
	public OrderTotalCalculator(Order order){
		this.orderLineItems = order.getOrderLineItems();
		this.discounts = order.getDiscounts();
		this.tax = order.getTax();
	}
	public double calculate(){
		calculateTotal();
		subtractDiscounts();
		calculateTax();
		return subtotal + tax;
	}
	/* Note from Sunny:
	 * I don't like how the below methods are void and are updating the members. 
	 * I'd rather prefer having them return values and update in caller. 
	 * But this is a "translation of the book"
	 */
	public void calculateTotal(){
		orderLineItems.forEach(orderLineItem -> {
			subtotal += orderLineItem.getPrice();
		});
	}
	public void subtractDiscounts(){
		discounts.forEach(discount -> {
			subtotal -= discount;
		});
	}
	public void calculateTax(){
		tax = subtotal * tax;
	}

}
