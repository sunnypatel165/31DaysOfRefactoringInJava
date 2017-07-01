This topic actually came up during the Remove Arrowhead Antipattern refactoring.
The refactoring introduces this as a side effect to remove the arrowhead. 
To eliminate the arrowhead you return as soon as possible.

```Java
public class Order {
	public Customer customer;
	double orderTotal = 0;

	public double calculateOrder(Customer customer, List<Product> products, double discounts) {
		this.customer = customer;

		if (products.size() > 0) {
			products.forEach(product -> orderTotal += product.getPrice());
			if (discounts > 0) {
				orderTotal -= discounts;
			}
		}
		return orderTotal;
	}
}
```
The idea is that as soon as you know what needs to be done and you have all the required information, you should exit the method as soon as possible and not continue along.

```Java
public class Order {
	public Customer customer;
	double orderTotal = 0;

	public double CalculateOrder(Customer customer, List<Product> products, double discounts) {
		if (products.size() == 0)
			return 0;

		this.customer = customer;
		products.forEach(product -> orderTotal += product.getPrice());

		if (discounts == 0)
			return orderTotal;

		orderTotal -= discounts;
		return orderTotal;
	}
}
```
#### Original C# code from the book:
```cs
public class Order {
	public Customer Customer { get; private set; }
	public decimal CalculateOrder(Customer customer, IEnumerable<Product> products, decimal discounts) {
		Customer = customer;
		decimal orderTotal = 0m;
		
		if (products.Count() > 0) {
			orderTotal = products.Sum(p => p.Price);
			if (discounts > 0) {
					orderTotal -= discounts;
			}
		}
		return orderTotal;
	}
}

//Solution:
 
public class Order {
	public Customer Customer { get; private set; }
	public decimal CalculateOrder(Customer customer, IEnumerable<Product> products, decimal discounts) {
		if (products.Count() == 0)
			return 0;
			
		Customer = customer;
		decimal orderTotal = products.Sum(p => p.Price);
		
		if (discounts == 0)
			return orderTotal;
		
		orderTotal -= discounts;
		return orderTotal;
	}
} 
