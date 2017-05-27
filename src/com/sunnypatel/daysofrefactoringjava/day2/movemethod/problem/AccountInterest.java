package com.sunnypatel.daysofrefactoringjava.day2.movemethod.problem;

/*
 * The point of interest here is the BankAccount.CalculateInterest method. 
 * A hint that you need the Move Method refactoring is when another class is using a method more often then the class in which it lives. 
 * If this is the case it makes sense to move the method to the class where it is primarily used.
 * This doesn’t work in every instance because of dependencies, but it is overlooked often as a worthwhile change. 
 */
public class AccountInterest {
	private BankAccount bankAccount;

	public AccountInterest(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public double getInterestRate() {
		return bankAccount.calculateInterestRate();
	}

	public boolean isIntroductoryRate() {
		return bankAccount.calculateInterestRate() < 0.05;
	}

}
