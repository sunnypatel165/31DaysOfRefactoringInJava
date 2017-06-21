Today’s refactoring didn’t really come from any one source. 
It just named it although someone else may have something similar that’s named differently. 
If you know of anyone that has a name for this other than Break Method, please let me know.

This refactoring is kind of a meta-refactoring in the fact that it’s just extract method applied over and over until you decompose one large method into several smaller methods. 

This example here is a tad contrived because the AcceptPayment method isn’t doing as much as I wanted.
Imagine that there is much more supporting code around each action that the one method is doing. 
That would match a real world scenario if you can picture it that way.
Below we have the AcceptPayment method that can be decomposed multiple times into distinct methods.

```Java
public class Product {
	private double price;
	private double discount;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}

public class Customer {
	public void deductFromCustomerBalance(double amount){
		//deduct from balance
	}
}

public class CashRegister {
	private static final double tax = 0.06;

	public void acceptPayment(Customer customer, List<Product> products, double payment) {
		double subTotal = 0;
		for(Product product: products){
			subTotal += product.getPrice();
		}
		for(Product product: products){
			subTotal -= product.getDiscount();
		}
		double grandTotal = subTotal + subTotal * tax;
		customer.deductFromCustomerBalance(grandTotal);

	}
}
```
As you can see the AcceptPayment method has a couple of things that can be decomposed into targeted
methods. So we perform the Extract Method refactoring a number of times until we come up with the
result: 
public class Product {
	private double price;
	private double discount;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}

public class Customer {
	public void deductFromCustomerBalance(double amount){
		//deduct from balance
	}
}

public class CashRegister {
	private static final double tax = 0.06;

	//This is an impure method - not a good thing. 
	public void acceptPayment(Customer customer, List<Product> products, double payment) {
		double subTotal = calculateSubTotal(products);
		subTotal = subtractDiscounts(products);
		double grandTotal = addTax(subTotal);
		subtractFromCustomerBalance(customer, grandTotal);
	}

	private double calculateSubTotal(List<Product> products) {
		double subTotal = 0.0;
		for (Product product : products) {
			subTotal += product.getPrice();
		}
		return subTotal;
	}

	private double subtractDiscounts(List<Product> products) {
		double subTotal = 0.0;
		for (Product product : products) {
			subTotal -= product.getDiscount();
		}
		return subTotal;
	}

	private double addTax(double subTotal) {
		return subTotal + subTotal * tax;
	}

	private void subtractFromCustomerBalance(Customer customer, double amount) {
		customer.deductFromCustomerBalance(amount);
	}
}
```

#### Original C# code from the book:
```cs
public class CashRegister {
	public CashRegister() {
		Tax = 0.06m;
	}
	
	private decimal Tax { get; set; }
	
	public void AcceptPayment(Customer customer, IEnumerable<Product> products,decimal payment) {
		decimal subTotal = 0m;
		foreach (Product product in products) {
			subTotal += product.Price;
		}
		foreach(Product product in products) {
			subTotal -= product.AvailableDiscounts;
		}
		decimal grandTotal = subTotal * Tax;
		customer.DeductFromAccountBalance(grandTotal);
	}
}
public class Customer {
	public void DeductFromAccountBalance(decimal amount) {
  		// deduct from balance
  		}
}
public class Product {
	public decimal Price { get; set; }
	public decimal AvailableDiscounts { get; set; }
} 

//Solution:
public class CashRegister {
	public CashRegister() {
		Tax = 0.06m;
	}
	
	private decimal Tax { get; set; }
	private IEnumerable<Product> Products { get; set; }
	
	public void AcceptPayment(Customer customer, IEnumerable<Product> products, decimal payment) {
		decimal subTotal = CalculateSubtotal();
		subTotal = SubtractDiscounts(subTotal);
		decimal grandTotal = AddTax(subTotal);
		SubtractFromCustomerBalance(customer, grandTotal);
	}
	
	private void SubtractFromCustomerBalance(Customer customer, decimal grandTotal) {
		customer.DeductFromAccountBalance(grandTotal);
	}
 	private decimal AddTax(decimal subTotal) {
 		return subTotal * Tax;
 	}
 	private decimal SubtractDiscounts(decimal subTotal) {
 		foreach(Product product in Products) {
 			subTotal -= product.AvailableDiscounts;
 		}
 		return subTotal;
 	}
 	private decimal CalculateSubtotal() {
 		decimal subTotal = 0m;
 		foreach (Product product in Products) {
 			subTotal += product.Price;
 		}
 		return subTotal;
 	}
}

public class Customer {
	public void DeductFromAccountBalance(decimal amount) {
		// deduct from balance
	}
}

public class Product {
	public decimal Price { get; set; }
	public decimal AvailableDiscounts { get; set; }
}