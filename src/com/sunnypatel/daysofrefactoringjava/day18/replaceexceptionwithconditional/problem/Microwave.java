package com.sunnypatel.daysofrefactoringjava.day18.replaceexceptionwithconditional.problem;

public class Microwave {
	private MicrowaveMotor motor;
	public boolean cook(String dish){
		boolean cooked = false;
		try{
			motor.cook(dish);
			cooked = true;
		}
		catch(InUseException e){
			cooked = false;
		}
		return cooked;
	}
}
