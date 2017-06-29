package com.sunnypatel.daysofrefactoringjava.day29.removemiddleman.problem;

public class Consumer {
	private AccountManager accountManager;

	public Consumer(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public Account get(int id) {
		return getAccountManager().getAccount(id);
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
