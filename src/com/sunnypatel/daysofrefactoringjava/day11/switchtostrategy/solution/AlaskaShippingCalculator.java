package com.sunnypatel.daysofrefactoringjava.day11.switchtostrategy.solution;

public class AlaskaShippingCalculator implements ShippingCalculator {

	@Override
	public int calculate() {
		return 15;
	}
}
