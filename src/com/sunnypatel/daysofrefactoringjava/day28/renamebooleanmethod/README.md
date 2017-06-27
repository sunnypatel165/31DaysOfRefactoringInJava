Today’s refactoring doesn’t necessarily come from Fowlers refactoring catalog. 
If anyone knows where this “refactoring” actually comes from, please let me know.
Granted, this could be viewed as not being a refactoring as the methods are actually changing, but this is a gray area and open to debate.
Methods with a large number of boolean parameters can quickly get out of hand and can produce unexpected behavior. 
Depending on the number of parameters will determine how many methods need to be broken out. 
Let’s take a look at where this refactoring starts:
```Java
public class BankAccount {
	public void createAccount(Customer customer, boolean withChecking, boolean withSavings, boolean withStocks) {
		// do work
	}
}
```
We can make this work a little better simple by exposing the boolean parameters via well named methods and in turn make the original method private to prevent anyone from calling it going forward. 
Obviously you could have a large number of permutations here and perhaps it makes more sense to refactor to a [Parameter object](http://refactoring.com/catalog/introduceParameterObject.html) instead.

```Java
public class BankAccount {
	public void CreateAccountWithChecking(Customer customer) {
		CreateAccount(customer, true, false);
	}

	//Note from Sunny:
	//This example is not a good design - ideally you'd have different classes for different account types!
	//And no impure functions!
	
	public void CreateAccountWithCheckingAndSavings(Customer customer) {
		CreateAccount(customer, true, true);
	}

	private void CreateAccount(Customer customer, boolean withChecking, boolean withSavings) {
		// do work
	}
}
```
 
#### Original C# code from the book
```cs
public class BankAccount {
	public void CreateAccount(Customer customer, bool withChecking, bool withSavings, bool withStocks) {
		// do work
	}
}
//Solution:
public class BankAccount {
	public void CreateAccountWithChecking(Customer customer) {
		CreateAccount(customer, true, false);
	}
	public void CreateAccountWithCheckingAndSavings(Customer customer) {
		CreateAccount(customer, true, true);
	}
	private void CreateAccount(Customer customer, bool withChecking, bool withSavings) {
		// do work
	}
}
``` 