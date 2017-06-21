package com.sunnypatel.daysofrefactoringjava.day22.breakmethod.problem;

import java.util.List;

public class CashRegister {
	private static final double tax = 0.06;

	public void acceptPayment(Customer customer, List<Product> products, double payment) {
		double subTotal = 0;
		for(Product product: products){
			subTotal += product.getPrice();
		}
		for(Product product: products){
			subTotal -= product.getDiscount();
		}
		double grandTotal = subTotal + subTotal * tax;
		customer.deductFromCustomerBalance(grandTotal);

	}
}
