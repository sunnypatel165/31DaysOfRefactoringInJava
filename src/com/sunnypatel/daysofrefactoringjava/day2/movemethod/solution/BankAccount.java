package com.sunnypatel.daysofrefactoringjava.day2.movemethod.solution;

import com.sunnypatel.daysofrefactoringjava.day2.movemethod.solution.AccountInterest;

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

}
