package com.sunnypatel.daysofrefactoringjava.day10.extractmethod.solution;

import java.util.ArrayList;
import java.util.List;

/*
 * You can see that the CalculateGrandTotal method is actually doing three different things here. 
 * It’s calculating the subtotal, applying any discounts and then calculating the tax for the receipt. 
 * Instead of making a developer look through that whole method to determine what each thing is doing, it would save time and readability to seperate those distinct tasks into their own methods like so:
 */
public class Receipt {
	private List<Integer> discounts = new ArrayList<>();
	private List<Integer> itemTotals = new ArrayList<>();

	public double CalculateGrandTotal()
    {
         double subTotal = calculateItemsTotal();
         subTotal = calculateDiscounts(subTotal);
         subTotal = calculateTax(subTotal);
         return subTotal;
     }
	private double calculateItemsTotal(){
		double subTotal=0.0;
		for(Integer item : itemTotals)
			subTotal += item;
		return subTotal;
	}
	private double calculateDiscounts(double subTotal){
		for(Integer discount: discounts)
			subTotal -= discount;
		return subTotal;
	}
	private double calculateTax(double subTotal){
		return subTotal + 0.65 * subTotal;
	}
}
