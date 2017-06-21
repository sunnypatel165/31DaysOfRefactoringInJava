package com.sunnypatel.daysofrefactoringjava.day22.breakmethod.solution;

import java.util.List;

import com.sunnypatel.daysofrefactoringjava.day22.breakmethod.solution.Customer;
import com.sunnypatel.daysofrefactoringjava.day22.breakmethod.solution.Product;

public class CashRegister {
	private static final double tax = 0.06;

	//This is an impure method - not a good thing. 
	public void acceptPayment(Customer customer, List<Product> products, double payment) {
		double subTotal = calculateSubTotal(products);
		subTotal = subtractDiscounts(products);
		double grandTotal = addTax(subTotal);
		subtractFromCustomerBalance(customer, grandTotal);
	}

	private double calculateSubTotal(List<Product> products) {
		double subTotal = 0.0;
		for (Product product : products) {
			subTotal += product.getPrice();
		}
		return subTotal;
	}

	private double subtractDiscounts(List<Product> products) {
		double subTotal = 0.0;
		for (Product product : products) {
			subTotal -= product.getDiscount();
		}
		return subTotal;
	}

	private double addTax(double subTotal) {
		return subTotal + subTotal * tax;
	}

	private void subtractFromCustomerBalance(Customer customer, double amount) {
		customer.deductFromCustomerBalance(amount);
	}
}
