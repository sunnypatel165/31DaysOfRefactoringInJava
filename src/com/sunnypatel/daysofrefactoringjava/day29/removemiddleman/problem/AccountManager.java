package com.sunnypatel.daysofrefactoringjava.day29.removemiddleman.problem;

public class AccountManager {
	private AccountDataProvider dataProvider;

	public AccountManager(AccountDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	public Account getAccount(int id) {
		return dataProvider.getAccount(id);
	}

}
