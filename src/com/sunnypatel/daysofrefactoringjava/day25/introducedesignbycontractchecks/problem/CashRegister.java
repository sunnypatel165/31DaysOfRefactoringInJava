package com.sunnypatel.daysofrefactoringjava.day25.introducedesignbycontractchecks.problem;

import java.util.List;

public class CashRegister {

	private double orderTotal = 0;

	public double calculateOrderTotal(List<Product> products, Customer customer) {
		orderTotal = 0;
		products.forEach(product -> orderTotal += product.getPrice());
		customer.addBalance(orderTotal); // impure!!!
		return orderTotal;

	}

}
