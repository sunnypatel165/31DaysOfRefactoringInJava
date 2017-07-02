The last day of refactoring comes from Fowlers refactoring catalog and can be found [here](http://refactoring.com/catalog/replaceConditionalWithPolymorphism.html).
This shows one of the foundations of Object Oriented Programming which is [Polymorphism](http://en.wikipedia.org/wiki/Type_polymorphism). 
The concept here is that in instances where you are doing checks by type, and performing some type of operation, it’s a good idea to encapsulate that algorithm within the class and then use polymorphism to abstract the call to the code.
```Java
public abstract class Customer {
}
public class Employee extends Customer{
}
public class NonEmployee extends Customer {
}

public class Product {
	public double getPrice(){
		return 1.0;
	}
}

public class OrderProcessor {
	double orderTotal = 0;

	public double processOrder(Customer customer, List<Product> products) {
		orderTotal = 0;
		products.forEach(product -> orderTotal += product.getPrice());
		if (customer instanceof Employee) {
			orderTotal -= orderTotal * 0.15;
		} else if (customer instanceof NonEmployee) {
			orderTotal -= orderTotal * 0.05;
		}
		return orderTotal;
	}
}
``` 
As you can see here, we’re not leaning on our inheritance hierarchy to put the calculation, or even the data needed to perform the calculation lest we have a SRP violation. 
So to refactor this we simply take the percentage rate and place that on the actual customer type that each class will then implement. 
I know this is really remedial but I wanted to cover this as well as I have seen it in code.

```Java
public abstract class Customer {
	public abstract double getDiscountPercent();
}
public class Employee extends Customer {

	@Override
	public double getDiscountPercent() {
		return 15;
	}
}
public class NonEmployee extends Customer {

	@Override
	public double getDiscountPercent() {
		return 5;
	}
}
public class Product {
	public double getPrice(){
		return 1.0;
	}
}
public class OrderProcessor {
	double orderTotal = 0;

	public double processOrder(Customer customer, List<Product> products) {
		orderTotal = 0;
		products.forEach(product -> orderTotal += product.getPrice());
		orderTotal -= customer.getDiscountPercent();
		return orderTotal;
	}
}
```

#### Original C# code from the book
```cs
public abstract class Customer {
}

public class Employee : Customer {
}

public class NonEmployee : Customer {
}

public class OrderProcessor {
	public decimal ProcessOrder(Customer customer, IEnumerable<Product> products) {
		// do some processing of order
		decimal orderTotal = products.Sum(p => p.Price);
		Type customerType = customer.GetType();
		if (customerType == typeof(Employee)) {
			orderTotal -= orderTotal * 0.15m;
		}
		else if (customerType == typeof(NonEmployee)) {
			orderTotal -= orderTotal * 0.05m;
		}
		return orderTotal;
	}
}

//Solution:
public abstract class Customer {
	public abstract decimal DiscountPercentage { get; }
}

public class Employee : Customer {
	public override decimal DiscountPercentage {
		get { return 0.15m; } 
	}
}
public class NonEmployee : Customer {
	public override decimal DiscountPercentage {
		get { return 0.05m; }
	}
}
public class OrderProcessor {
	public decimal ProcessOrder(Customer customer, IEnumerable<Product> products) {
		// do some processing of order
		decimal orderTotal = products.Sum(p => p.Price);
		orderTotal -= orderTotal * customer.DiscountPercentage;
		return orderTotal;
	}
}
```