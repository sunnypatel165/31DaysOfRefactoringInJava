package com.sunnypatel.daysofrefactoringjava.day19.extractfactoryclass.problem;

public class PoliceCar {
	private int mileage;
	private boolean serviceRequired;
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public boolean isServiceRequired() {
		return serviceRequired;
	}
	public void setServiceRequired(boolean serviceRequired) {
		this.serviceRequired = serviceRequired;
	}

}
