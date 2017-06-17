package com.sunnypatel.daysofrefactoringjava.day19.extractfactoryclass.solution;

/*
 * In this instance we can extract our creation code and place in a Factory class that has the distinct responsibility of create instances of PoliceCar’s.
 */
public interface PoliceCarFactory {
	public PoliceCar create(int mileage, boolean serviceRequired);

}
