package com.sunnypatel.daysofrefactoringjava.day5.pullupfield.problem;

/*
 * In this example, we have a constant value that is duplicated between two derived classes. 
 * To promote reuse we can pull up the field into the base class and rename it for brevity.
 * At the same time we can pull up the corresponding getter as learned yesterday ;)
 */
public abstract class Account {
	public abstract int getMinimumBalance();

}
