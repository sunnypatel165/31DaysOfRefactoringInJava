package com.sunnypatel.daysofrefactoringjava.day30.returnasap.problem;

import java.util.List;

public class Order {
	public Customer customer;
	double orderTotal = 0;

	public double calculateOrder(Customer customer, List<Product> products, double discounts) {
		this.customer = customer;

		if (products.size() > 0) {
			products.forEach(product -> orderTotal += product.getPrice());
			if (discounts > 0) {
				orderTotal -= discounts;
			}
		}
		return orderTotal;
	}
}
