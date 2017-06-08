package com.sunnypatel.daysofrefactoringjava.day13.extractmethodobjects.solution;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<OrderLineItem> orderLineItems = new ArrayList<>();
	private List<Double> discounts = new ArrayList<>();
	private double tax;
	
	public double calculate(){
		return new OrderTotalCalculator(this).calculate();
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
