package com.sunnypatel.daysofrefactoringjava.day19.extractfactoryclass.solution;

import com.sunnypatel.daysofrefactoringjava.day19.extractfactoryclass.solution.PoliceCar;

public class PoliceCarController {
	private PoliceCarFactory policeCarFactory;
	public PoliceCarController(PoliceCarFactory policeCarFactory){
		this.policeCarFactory = policeCarFactory;
	}
	public PoliceCar createPoliceCar(int mileage, boolean serviceRequired){
		return policeCarFactory.create(mileage, serviceRequired);
	}

}
