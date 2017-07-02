package com.sunnypatel.daysofrefactoringjava.day31.replaceconditionalwithpolymorphism.solution;

public class NonEmployee extends Customer {

	@Override
	public double getDiscountPercent() {
		return 5;
	}

}
