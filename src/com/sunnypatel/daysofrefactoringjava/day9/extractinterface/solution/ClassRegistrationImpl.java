package com.sunnypatel.daysofrefactoringjava.day9.extractinterface.solution;

public class ClassRegistrationImpl implements ClassRegistration {

	@Override
	public void create() {
		//creation code
	}

	private int total;
	@Override
	public int getTotal() {
		return total;
	}

}
