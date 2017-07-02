package com.sunnypatel.daysofrefactoringjava.day31.replaceconditionalwithpolymorphism.problem;

import java.util.List;

public class OrderProcessor {
	double orderTotal = 0;

	public double processOrder(Customer customer, List<Product> products) {
		orderTotal = 0;
		products.forEach(product -> orderTotal += product.getPrice());
		if (customer instanceof Employee) {
			orderTotal -= orderTotal * 0.15;
		} else if (customer instanceof NonEmployee) {
			orderTotal -= orderTotal * 0.05;
		}
		return orderTotal;
	}
}