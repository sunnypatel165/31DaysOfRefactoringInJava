Today’s refactoring comes from Fowler’s refactoring catalog and can be found [here](http://www.refactoring.com/catalog/removeDoubleNegative.html).

This refactoring is pretty simple to implement although I find it in many codebases that severely hurts readability and almost always conveys incorrect intent.
This type of code does the most damage because of the assumptions made on it.
Assumptions lead to incorrect maintenance code written, which in turn leads to bugs. 
Take the following example:

```Java
public class Customer {
	private String name;
	private double balance;

	public Customer(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	public boolean isNotFlagged(){
		return balance > 30.0; //magic number!
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}
}
public class Order {
	public void checkout(List<Product> products, Customer customer){
		if(!customer.isNotFlagged()){
			//the customer is flagged, can't process
			return;
		}
		//do normal order checkout
	}
}
```
As you can see the double negative here is difficult to read because we have to figure out what is positive state of the two negatives.
The fix is very easy. 
If we don’t have a positive test, add one that does the double negative assertion for you rather than make sure you get it correct.

```Java
public class Customer {
	private String name;
	private double balance;

	public Customer(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	public boolean isFlagged(){
		return balance <= 30.0; //magic number!
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}
}

public class Order {
	public void checkout(List<Product> products, Customer customer){
		if(customer.isFlagged()){
			//the customer is flagged, can't process
			return;
		}
		//do normal order checkout
	}
}
```

#### Original C# Code from the book
```cs
public class Order {
	public void Checkout(IEnumerable<Product> products, Customer customer) {
		if (!customer.IsNotFlagged) {
			// the customer account is flagged
			// log some errors and return
			return;
		}
		// normal order processing
	}
}
public class Customer {
	public decimal Balance { get; private set; }
	public bool IsNotFlagged {
		get { return Balance < 30m; }
	}
}

//Solution:

public class Order {
	public void Checkout(IEnumerable<Product> products, Customer customer) {
		if (customer.IsFlagged) {
			// the customer account is flagged
			// log some errors and return
			return;
		}
		// normal order processing
	}
}
public class Customer {
	public decimal Balance { get; private set; }
	public bool IsFlagged {
		get { return Balance >= 30m; }
	} 
}
``` 
