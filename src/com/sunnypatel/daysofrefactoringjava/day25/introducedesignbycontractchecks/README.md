Design By Contract or DBC defines that methods should have defined input and output verifications.
Therefore, you can be sure you are always working with a usable set of data in all methods and everything is behaving as expected. 
If not, exceptions or errors should be returned and handled from the methods. 
To read more on DBC read the wikipedia page [here](http://en.wikipedia.org/wiki/Design_by_contract).

In our example here, we are working with input parameters that may possibly be null. 
As a result a NullPointerException would be thrown from this method because we never verify that we have an instance.
During the end of the method, we don’t ensure that we are returning a valid decimal to the consumer of this method and may introduce methods elsewhere.
```Java
public class Product {
	private String name;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
public class Customer {
	private String name;
	private double balance;

	public void addBalance(double amount) {
		balance += amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}
}
public class CashRegister {

	private double orderTotal = 0;

	public double calculateOrderTotal(List<Product> products, Customer customer) {
		orderTotal = 0;
		products.forEach(product -> orderTotal += product.getPrice());
		customer.addBalance(orderTotal); // impure!!!
		return orderTotal;
	}
}

The changes we can make here to introduce DBC checks is pretty easy. 
First we will assert that we don’t have a null customer, check that we have at least one product to total. 
Before we return the order total we will ensure that we have a valid amount for the order total. 
If any of these checks fail in this example we should throw targeted exceptions that detail exactly what happened and fail gracefully rather than throw an obscure NullPointrException.
Not for Java: ~~It seems as if there is some DBC framework methods and exceptions in the Microsoft.Contracts namespace that was introduced with .net framework 3.5. I personally haven’t played with these yet, but they may be worth looking at. This is the only thing I could find on msdn about the namespace.~~

```Java
public class Product {
	private String name;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}

public class Customer {
	private String name;
	private double balance;

	public void addBalance(double amount) {
		balance += amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}
}

public class CashRegister {

	private double orderTotal = 0;

	public double calculateOrderTotal(List<Product> products, Customer customer) {
		if (customer == null || products == null || products.size() == 0)
			throw new IllegalArgumentException("One of the arguments passed is null!");

		orderTotal = 0;
		products.forEach(product -> orderTotal += product.getPrice());

		if (orderTotal == 0)
			throw new RuntimeException("Not a correct order, please check!");

		customer.addBalance(orderTotal); // impure!!!
		return orderTotal;

	}
}
```

It does add more code to the method for validation checks and you can go overboard with DBC, but I think
in most scenarios it is a worthwhile endeavor to catch sticky situations. It really stinks to chase after a
NullReferenceException without detailed information. 


#### Original C# code from the book:
public class CashRegister {
	public decimal TotalOrder(IEnumerable<Product> products, Customer customer) {
		decimal orderTotal = products.Sum(product => product.Price);
		customer.Balance += orderTotal;
		return orderTotal;
	}
}

//Solution:
public class CashRegister {
	public decimal TotalOrder(IEnumerable<Product> products, Customer customer) {
	
		if (customer == null)
			throw new ArgumentNullException("customer", "Customer cannot be null");
	
		if (products.Count() == 0)
			throw new ArgumentException("Must have at least one product to total", "products");
	
		decimal orderTotal = products.Sum(product => product.Price);
		customer.Balance += orderTotal;
	
		if (orderTotal == 0)
			throw new ArgumentOutOfRangeException("orderTotal", "Order Total should not be zero");
	
		return orderTotal;
	}
}