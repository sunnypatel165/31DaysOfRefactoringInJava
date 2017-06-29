package com.sunnypatel.daysofrefactoringjava.day29.removemiddleman.solution;

public class Consumer {
	private AccountDataProvider accountDataProvider;

	public Consumer(AccountDataProvider accountDataProvider) {
		this.accountDataProvider = accountDataProvider;
	}

	public Account get(int id) {
		return getAccountDataProvider().getAccount(id);
	}

	public AccountDataProvider getAccountDataProvider() {
		return accountDataProvider;
	}

	public void setAccountDataProvider(AccountDataProvider accountDataProvider) {
		this.accountDataProvider = accountDataProvider;
	}
}
