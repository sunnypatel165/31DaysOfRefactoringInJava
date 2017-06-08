package com.sunnypatel.daysofrefactoringjava.day11.switchtostrategy.problem;

public class Client {
	public int calculateShipping() {
		ShippingInfo shippingInfo = new ShippingInfo();
		return shippingInfo.calculateShippingAmount(State.Alaska);
	}
}
