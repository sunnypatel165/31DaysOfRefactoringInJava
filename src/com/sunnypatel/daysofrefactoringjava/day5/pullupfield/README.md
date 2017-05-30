Today we look at a refactoring that is similar to the Pull Up method. Instead of a method, it is obviously done with a field instead!

 ``` Java
 public abstract class Account {
	public abstract int getMinimumBalance();
}

public class CheckingAccount extends Account{
	private int minimumAccountBalance = 1000;

	@Override
	public int getMinimumBalance() {
		return minimumAccountBalance;
	}

}
public class SavingsAccount extends Account {
	private int minimumAccountBalance = 1000;

	@Override
	public int getMinimumBalance() {
		return minimumAccountBalance;
	}
	
}
```
In this example, we have a constant value that is duplicated between two derived classes. To promote reuse we can pull up the field into the base class and rename it for brevity.

```Java
public abstract class Account {
	private static final int minimumAccountBalance = 1000;
	public int getMinimumAccountBalance(){
		return minimumAccountBalance;
	}

}

public class CheckingAccount extends Account {
	//minimum balance is available from the parent!

}

public class SavingsAccount extends Account {
	//minimum balance is available from the parent!

}
```
  
  
 #### Original C# code from the book:
 ``` 
   1: public abstract class Account
   2: {
   3: }
   4:  
   5: public class CheckingAccount : Account
   6: {
   7:     private decimal _minimumCheckingBalance = 5m;
   8: }
   9:  
  10: public class SavingsAccount : Account
  11: {
  12:     private decimal _minimumSavingsBalance = 5m;
  13: }
  
  
   1: public abstract class Account
   2: {
   3:     protected decimal _minimumBalance = 5m;
   4: }
   5:  
   6: public class CheckingAccount : Account
   7: {
   8: }
   9:  
  10: public class SavingsAccount : Account
  11: {
  12: }
  ```