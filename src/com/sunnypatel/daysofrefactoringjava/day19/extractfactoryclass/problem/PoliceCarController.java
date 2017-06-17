package com.sunnypatel.daysofrefactoringjava.day19.extractfactoryclass.problem;

/*
 * As we can see, the createPoliceCar is responsible for creating a PoliceCar and then setting some initial properties on the police car depending on some external input. 
 * This works fine for simple setup, but over time this can grow and the burden of creating the police car is put on the controller which isn’t really something that the controller should be tasked with.
 */
public class PoliceCarController {
	public PoliceCar createPoliceCar(int mileage, boolean serviceRequired){
		PoliceCar car = new PoliceCar();
		car.setMileage(mileage);
		car.setServiceRequired(serviceRequired);
		//and some other logic as the system grows
		return car;	
	}
}
