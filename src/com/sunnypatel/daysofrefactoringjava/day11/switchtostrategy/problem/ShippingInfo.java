package com.sunnypatel.daysofrefactoringjava.day11.switchtostrategy.problem;

public class ShippingInfo {
	public int calculateShippingAmount(State shipToState) {
		switch (shipToState) {
			case Alaska:
				return getAlaskaShippingAmount();
			case NewYork:
				return getNewYorkShippingAmount();
			case Florida:
				return getFloridaShippingAmount();
			default:
				return 0;
		}
	}

	private int getFloridaShippingAmount() {
		return 15;
	}

	private int getNewYorkShippingAmount() {
		return 10;
	}

	private int getAlaskaShippingAmount() {
		return 3;
	}

}
