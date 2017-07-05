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
}
```

Simple enough!

#### Original C# code from the book:
```cs
public class BankAccount
{
    public BankAccount(int accountAge, int creditScore, AccountInterest accountInterest)
    {
        AccountAge = accountAge;
        CreditScore = creditScore;
        AccountInterest = accountInterest;
    }

    public int AccountAge { get; private set; }
    public int CreditScore { get; private set; }
    public AccountInterest AccountInterest { get; private set; }

    public double CalculateInterestRate()
    {
        if (CreditScore > 800)
            return 0.02;

        if (AccountAge > 10)
            return 0.03;

        return 0.05;
    }
}

public class AccountInterest
{
    public BankAccount Account { get; private set; }

    public AccountInterest(BankAccount account)
    {
        Account = account;
    }

    public double InterestRate
    {
        get { return Account.CalculateInterestRate(); }
    }

    public bool IntroductoryRate
    {
        get { return Account.CalculateInterestRate() < 0.05; }
    }
}
```

```cs
public class BankAccount
{
    public BankAccount(int accountAge, int creditScore, AccountInterest accountInterest)
    {
        AccountAge = accountAge;
        CreditScore = creditScore;
        AccountInterest = accountInterest;
    }

    public int AccountAge { get; private set; }
    public int CreditScore { get; private set; }
    public AccountInterest AccountInterest { get; private set; }
}

public class AccountInterest
{
    public BankAccount Account { get; private set; }

    public AccountInterest(BankAccount account)
    {
        Account = account;
    }

    public double InterestRate
    {
        get { return CalculateInterestRate(); }
    }

    public bool IntroductoryRate
    {
        get { return CalculateInterestRate() < 0.05; }
    }

    public double CalculateInterestRate()
    {
        if (Account.CreditScore > 800)
            return 0.02;

        if (Account.AccountAge > 10)
            return 0.03;

        return 0.05;
    }
}
```
