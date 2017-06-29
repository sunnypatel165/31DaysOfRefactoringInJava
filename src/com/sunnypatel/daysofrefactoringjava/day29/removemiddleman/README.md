Today’s refactoring comes from Fowler’s refactoring catalog and can be found [here](http://refactoring.com/catalog/removeMiddleMan.html).
Sometimes in code you may have a set of “Phantom” or “Ghost” classes. 
Fowler calls these “Middle Men”. Middle Men classes simply take calls and forward them on to other components without doing any work.
This is an unneeded layer and can be removed completely with minimal effort.

```Java
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
public class AccountManager {
	private AccountDataProvider dataProvider;

	public AccountManager(AccountDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	public Account getAccount(int id) {
		return dataProvider.getAccount(id);
	}
}

public class AccountDataProvider {
	public Account getAccount(int id) {
		//do some logic
		return null;
	}
}

```

The end result is straightforward enough. 
We just remove the middle man object and point the original call to the intended receiver.

```Java
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
public class AccountDataProvider {
	public Account getAccount(int id) {
		//do some logic
		return null;
	}
}
```
 
 
#### Original C# code from the book
```cs
public class Consumer {
	public AccountManager AccountManager { get; set; }
	public Consumer(AccountManager accountManager) {
		AccountManager = accountManager;
	}
	public void Get(int id) {
		Account account = AccountManager.GetAccount(id);
	}
}
public class AccountManager {
	public AccountDataProvider DataProvider { get; set; }
	public AccountManager(AccountDataProvider dataProvider) {
		DataProvider = dataProvider;
	}
	public Account GetAccount(int id) {
		return DataProvider.GetAccount(id);
	}
}
public class AccountDataProvider {
	public Account GetAccount(int id) {
		// get account
	}
}

//Solution:
public class Consumer {
	public AccountDataProvider AccountDataProvider { get; set; }
	public Consumer(AccountDataProvider dataProvider) {
		AccountDataProvider = dataProvider;
	}
	public void Get(int id) {
		Account account = AccountDataProvider.GetAccount(id);
	}
}
public class AccountDataProvider {
	public Account GetAccount(int id) {
		// get account
	}
}
```