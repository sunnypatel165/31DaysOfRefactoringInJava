The refactoring today is pretty straightforward, although often overlooked and ignored as being a worthwhile refactoring. Move method does exactly what it sounds like, move a method to a better location. Let’s look at the following code before our refactoring:

```Java
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
```
The point of interest here is the BankAccount.CalculateInterest method. A hint that you need the Move Method refactoring is when another class is using a method more often then the class in which it lives. If this is the case it makes sense to move the method to the class where it is primarily used. This doesn’t work in every instance because of dependencies, but it is overlooked often as a worthwhile change.

In the end you would end up with something like this:

```Java
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
```

Simple enough!

#### Original C# code from the book:
```Java
   1: public class BankAccount
   2: {
   3:     public BankAccount(int accountAge, int creditScore, AccountInterest accountInterest)
   4:     {
   5:         AccountAge = accountAge;
   6:         CreditScore = creditScore;
   7:         AccountInterest = accountInterest;
   8:     }
   9:  
  10:     public int AccountAge { get; private set; }
  11:     public int CreditScore { get; private set; }
  12:     public AccountInterest AccountInterest { get; private set; }
  13:  
  14:     public double CalculateInterestRate()
  15:     {
  16:         if (CreditScore > 800)
  17:             return 0.02;
  18:  
  19:         if (AccountAge > 10)
  20:             return 0.03;
  21:  
  22:         return 0.05;
  23:     }
  24: }
  25:  
  26: public class AccountInterest
  27: {
  28:     public BankAccount Account { get; private set; }
  29:  
  30:     public AccountInterest(BankAccount account)
  31:     {
  32:         Account = account;
  33:     }
  34:  
  35:     public double InterestRate
  36:     {
  37:         get { return Account.CalculateInterestRate(); }
  38:     }
  39:  
  40:     public bool IntroductoryRate
  41:     {
  42:         get { return Account.CalculateInterestRate() < 0.05; }
  43:     }
  44: }
  
  
   1: public class BankAccount
   2: {
   3:     public BankAccount(int accountAge, int creditScore, AccountInterest accountInterest)
   4:     {
   5:         AccountAge = accountAge;
   6:         CreditScore = creditScore;
   7:         AccountInterest = accountInterest;
   8:     }
   9:  
  10:     public int AccountAge { get; private set; }
  11:     public int CreditScore { get; private set; }
  12:     public AccountInterest AccountInterest { get; private set; }
  13: }
  14:  
  15: public class AccountInterest
  16: {
  17:     public BankAccount Account { get; private set; }
  18:  
  19:     public AccountInterest(BankAccount account)
  20:     {
  21:         Account = account;
  22:     }
  23:  
  24:     public double InterestRate
  25:     {
  26:         get { return CalculateInterestRate(); }
  27:     }
  28:  
  29:     public bool IntroductoryRate
  30:     {
  31:         get { return CalculateInterestRate() < 0.05; }
  32:     }
  33:  
  34:     public double CalculateInterestRate()
  35:     {
  36:         if (Account.CreditScore > 800)
  37:             return 0.02;
  38:  
  39:         if (Account.AccountAge > 10)
  40:             return 0.03;
  41:  
  42:         return 0.05;
  43:     }
  44: }
 ```
```
