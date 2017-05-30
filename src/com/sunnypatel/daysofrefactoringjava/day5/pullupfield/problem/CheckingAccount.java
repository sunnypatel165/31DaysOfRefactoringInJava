package com.sunnypatel.daysofrefactoringjava.day5.pullupfield.problem;

public class CheckingAccount extends Account{
	private int minimumAccountBalance = 1000;

	@Override
	public int getMinimumBalance() {
		return minimumAccountBalance;
	}

}
