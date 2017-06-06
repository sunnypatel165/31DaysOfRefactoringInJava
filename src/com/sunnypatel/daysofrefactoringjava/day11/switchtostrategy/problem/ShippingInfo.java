package com.sunnypatel.daysofrefactoringjava.day11.switchtostrategy.problem;

public class ShippingInfo {
	public int CalculateShippingAmount(State shipToState) {
		switch (shipToState) {
		case Alaska:
			return GetAlaskaShippingAmount();
		case NewYork:
			return GetNewYorkShippingAmount();
		case Florida:
			return GetFloridaShippingAmount();
		default:
			return 0;
		}
	}

	private int GetFloridaShippingAmount() {
		return 15;
	}

	private int GetNewYorkShippingAmount() {
		return 10;
	}

	private int GetAlaskaShippingAmount() {
		return 3;
	}

}
