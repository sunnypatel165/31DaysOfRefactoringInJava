package com.sunnypatel.daysofrefactoringjava.day5.pullupfield.solution;

/*
 * In this example, we have a constant value that is duplicated between two derived classes. 
 * To promote reuse we have pulled up the field into the base class and rename it for brevity.
 * At the same time we have pulled up the corresponding getter as learned yesterday ;)
 */
public abstract class Account {
	private static final int minimumAccountBalance = 1000;
	public int getMinimumAccountBalance(){
		return minimumAccountBalance;
	}

}
