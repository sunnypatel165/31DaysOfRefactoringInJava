package com.sunnypatel.daysofrefactoringjava.day19.extractfactoryclass.solution;

import com.sunnypatel.daysofrefactoringjava.day19.extractfactoryclass.solution.PoliceCar;

/*
 * Now that we have the creation logic put off to a factory, we can add to that one class that is tasked with creating instances for us without the worry of missing something during setup or duplicating code. 
 */
public class PoliceCarFactoryImpl implements PoliceCarFactory {

	@Override
	public PoliceCar create(int mileage, boolean serviceRequired) {
		PoliceCar car = new PoliceCar();
		car.setMileage(mileage);
		car.setServiceRequired(serviceRequired);
		// and some other logic as the system grows

		return car;
	}

}
