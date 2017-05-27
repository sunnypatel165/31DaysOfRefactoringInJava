package com.sunnypatel.daysofrefactoringjava.day2.movemethod.problem;

/*
 * The refactoring today is pretty straightforward, although often overlooked and ignored as being a worthwhile refactoring.
 * Move method does exactly what it sounds like, move a method to a better location.
 */
public class BankAccount {
	private int accountAge;
	private int creditStore;
	private AccountInterest accountInterest;

	public BankAccount(int accountAge, int creditStore, AccountInterest accountInterest) {
		this.accountAge = accountAge;
		this.creditStore = creditStore;
		this.accountInterest = accountInterest;
	}

	public int getAccountAge() {
		return accountAge;
	}

	public void setAccountAge(int accountAge) {
		this.accountAge = accountAge;
	}

	public int getCreditStore() {
		return creditStore;
	}

	public void setCreditStore(int creditStore) {
		this.creditStore = creditStore;
	}

	public AccountInterest getAccountInterest() {
		return accountInterest;
	}

	public void setAccountInterest(AccountInterest accountInterest) {
		this.accountInterest = accountInterest;
	}

	public double calculateInterestRate() {
		if (creditStore > 800)
			return 0.02;
		if (accountAge > 10)
			return 0.03;

		return 0.05;
	}
}
