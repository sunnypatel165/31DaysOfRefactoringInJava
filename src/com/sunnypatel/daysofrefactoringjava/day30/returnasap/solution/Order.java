package com.sunnypatel.daysofrefactoringjava.day30.returnasap.solution;

import java.util.List;

public class Order {
	public Customer customer;
	double orderTotal = 0;

	public double CalculateOrder(Customer customer, List<Product> products, double discounts) {
		if (products.size() == 0)
			return 0;

		this.customer = customer;
		products.forEach(product -> orderTotal += product.getPrice());

		if (discounts == 0)
			return orderTotal;

		orderTotal -= discounts;
		return orderTotal;
	}
}