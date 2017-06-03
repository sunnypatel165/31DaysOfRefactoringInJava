package com.sunnypatel.daysofrefactoringjava.day9.extractinterface.problem;

/*
 * Today we look at an often overlooked refactoring. 
 * Extract Interface. 
 * When you notice more than one class using a similar subset of methods on a class, it is useful to break the dependency and introduce an interface that the consumers to use. 
 * It’s easy to implement and has benefits from loose coupling.
 */
public class RegistrationProcessor {
	public int processRegistration(ClassRegistration registration){
		registration.create();
		return registration.getTotal();
	}
}
