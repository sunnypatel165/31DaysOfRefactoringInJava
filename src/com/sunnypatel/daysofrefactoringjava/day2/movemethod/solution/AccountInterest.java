package com.sunnypatel.daysofrefactoringjava.day2.movemethod.solution;

public class AccountInterest {
	
	private BankAccount bankAccount;

	public AccountInterest(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public double getInterestRate(){
		return this.calculateInterestRate();
	}
	public boolean isIntroductoryRate(){
		return this.calculateInterestRate() < 0.05;
	}
	private double calculateInterestRate(){
		if(bankAccount.getCreditStore() > 800)
			return 0.02;
		if(bankAccount.getAccountAge() > 10)
			return 0.03;
		
		return 0.05;
	}

}
