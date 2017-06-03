package com.sunnypatel.daysofrefactoringjava.day9.extractinterface.solution;

import com.sunnypatel.daysofrefactoringjava.day9.extractinterface.solution.ClassRegistration;

public class RegistrationProcessor {
	public int processRegistration(ClassRegistration registration){
		registration.create();
		return registration.getTotal();
	}

}
