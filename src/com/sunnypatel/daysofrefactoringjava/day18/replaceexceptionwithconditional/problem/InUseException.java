package com.sunnypatel.daysofrefactoringjava.day18.replaceexceptionwithconditional.problem;

public class InUseException extends RuntimeException{

	private static final long serialVersionUID = -7115741647066213175L;
	public InUseException(String message){
		super(message);
	}
}
