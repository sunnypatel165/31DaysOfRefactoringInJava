package com.sunnypatel.daysofrefactoringjava.day31.replaceconditionalwithpolymorphism.solution;

import java.util.List;

public class OrderProcessor {
	double orderTotal = 0;

	public double processOrder(Customer customer, List<Product> products) {
		orderTotal = 0;
		products.forEach(product -> orderTotal += product.getPrice());
		orderTotal -= customer.getDiscountPercent();
		return orderTotal;
	}
}