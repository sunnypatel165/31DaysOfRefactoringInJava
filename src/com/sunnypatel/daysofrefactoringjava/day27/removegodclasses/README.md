Often with legacy code bases I will often come across classes that are clear [SRP](http://www.lostechies.com/blogs/sean_chambers/archive/2008/03/15/ptom-single-responsibility-principle.aspx) violations. 
Often these classes will be suffixed with either “Utils” or “Manager”. 
Sometimes they don’t have this indication and are just classes with multiple grouped pieces of functionality.
Another good indicator of a God class is methods grouped together with using statements or comments into separate roles that this one class is performing.
Over time, these classes become a dumping ground for a method that someone doesn’t have time/want to put in the proper class.
The refactoring for situations like these is to break apart the methods into distinct classes that are responsible for specific roles.

```Java
public class CustomerService {
	public double calculateOrderDiscount(List<Product> products, Customer customer) {
		// do work
		return 0.0;
	}

	public boolean customerIsValid(Customer customer, Order order) {
		// do work
		return false;
	}

	public List<String> gatherOrderErrors(List<Product> products, Customer customer) {
		// do work
		return null;
	}

	public void register(Customer customer) {
		// do work
	}

	public void forgotPassword(Customer customer) {
		// do work
	}
}
```
 
The refactoring for this is very straight forward. 
Simply take the related methods and place them in specific classes that match their responsibility.
This makes them much finer grained and defined in what they do and make future maintenance much easier.
Here is the end result of splitting up the methods above into two distinct classes.

```Java
public class CustomerOrderService {
	public double calculateOrderDiscount(List<Product> products, Customer customer) {
		// do work
		return 0.0;
	}

	public boolean customerIsValid(Customer customer, Order order) {
		// do work
		return false;
	}

	public List<String> gatherOrderErrors(List<Product> products, Customer customer) {
		// do work
		return null;
	}
}
public class CustomerRegistrationService {
	public void register(Customer customer) {
		// do work
	}

	public void forgotPassword(Customer customer) {
		// do work
	}
}
```

#### Original C# code from the book
```cs
public class CustomerService {
	public decimal CalculateOrderDiscount(IEnumerable<Product> products, Customer customer) {
		// do work
	}
	public bool CustomerIsValid(Customer customer, Order order) {
		// do work
	}
	public IEnumerable<string> GatherOrderErrors(IEnumerable<Product> products, Customer customer) {
		// do work
	}
	public void Register(Customer customer) {
		// do work
	}
	public void ForgotPassword(Customer customer) {
		// do work
	}
}
//Solution:
public class CustomerOrderService {
	public decimal CalculateOrderDiscount(IEnumerable<Product> products, Customer customer) {
		// do work
	}
	public bool CustomerIsValid(Customer customer, Order order) {
		// do work
	}
	public IEnumerable<string> GatherOrderErrors(IEnumerable<Product> products, Customer customer) {
		// do work
	}
}
public class CustomerRegistrationService {
	public void Register(Customer customer) {
		// do work
	}
	public void ForgotPassword(Customer customer) {
		// do work
	}
}
```