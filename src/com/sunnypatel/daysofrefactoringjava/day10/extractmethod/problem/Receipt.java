package com.sunnypatel.daysofrefactoringjava.day10.extractmethod.problem;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
	private List<Integer> discounts = new ArrayList<>();
	private List<Integer> itemTotals = new ArrayList<>();

	private double subTotal = 0.0;
	
	public double calculateGrandTotal(){
		itemTotals.forEach(item -> subTotal += item);
		
		if(discounts.size() > 0 )
			discounts.forEach(discount -> subTotal -= discount);
		
		double tax = subTotal * 0.065;
		
		subTotal = subTotal + tax;
		return subTotal;
	}
}
