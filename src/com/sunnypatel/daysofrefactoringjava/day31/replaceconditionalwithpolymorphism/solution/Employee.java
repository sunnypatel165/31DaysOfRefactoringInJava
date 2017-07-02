package com.sunnypatel.daysofrefactoringjava.day31.replaceconditionalwithpolymorphism.solution;

public class Employee extends Customer {

	@Override
	public double getDiscountPercent() {
		return 15;
	}

}
