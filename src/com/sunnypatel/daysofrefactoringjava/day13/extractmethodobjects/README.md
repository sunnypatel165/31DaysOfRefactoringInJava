Today's refactoring comes from Martin Fowlers list of refactorings. You can find his original article here
with a brief description.
This is a more infrequent refactoring that I see myself using but it comes in handy at times. When trying to
apply an Extract Method refactoring, and multiple methods are needing to be introduced, it is sometimes
gets ugly because of multiple local variables that are being used within a method. Because of this reason, it
is better to introduce an Extract Method Object refactoring and to segregate the logic required to perform
the task.

```Java
public class OrderLineItem {
	private double price;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
public class Order {
	private List<OrderLineItem> orderLineItems = new ArrayList<>();
	private List<Double> discounts = new ArrayList<>();
	private double tax = 0;
	private double netTax = 0;
	private double subtotal = 0;
	
	public double calculate(){
		orderLineItems.forEach(orderLineItem -> {
			subtotal += orderLineItem.getPrice();
		});
		discounts.forEach(discount -> {
			subtotal -= discount;
		});
		netTax = subtotal * tax;
		
		return subtotal+netTax;
	}
	public List<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}
	public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}
	public List<Double> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<Double> discounts) {
		this.discounts = discounts;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
}
```
This entails passing a reference to the class that will be returning the computation to a new object that has
the multiple methods via the constructor, or passing the individual parameters to the constructor of the
method object. I will be showing the former here.
```Java
public class OrderLineItem {
	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
public class Order {
	private List<OrderLineItem> orderLineItems = new ArrayList<>();
	private List<Double> discounts = new ArrayList<>();
	private double tax;
	
	public double calculate(){
		return new OrderTotalCalculator(this).calculate();
	}
	public List<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}
	public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}
	public List<Double> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<Double> discounts) {
		this.discounts = discounts;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
}
public class OrderTotalCalculator {
	private List<OrderLineItem> orderLineItems = new ArrayList<>();
	private List<Double> discounts = new ArrayList<>();
	private double tax;
	private double subtotal = 0;
	
	public OrderTotalCalculator(Order order){
		this.orderLineItems = order.getOrderLineItems();
		this.discounts = order.getDiscounts();
		this.tax = order.getTax();
	}
	public double calculate(){
		calculateTotal();
		subtractDiscounts();
		calculateTax();
		return subtotal + tax;
	}
	/* Note from Sunny:
	 * I don't like how the below methods are void and are updating the members. 
	 * I'd rather prefer having them return values and update in caller. 
	 * But this is a "translation of the book"
	 */
	public void calculateTotal(){
		orderLineItems.forEach(orderLineItem -> {
			subtotal += orderLineItem.getPrice();
		});
	}
	public void subtractDiscounts(){
		discounts.forEach(discount -> {
			subtotal -= discount;
		});
	}
	public void calculateTax(){
		tax = subtotal * tax;
	}
}
```

 
 #### Original C# code from the book:
 
 ```cs
public class OrderLineItem {
	public decimal Price { get; private set; }
}
public class Order {
	private IList<OrderLineItem> OrderLineItems { get; set; }
	private IList<decimal> Discounts { get; set; }
 	private decimal Tax { get; set; }
	public decimal Calculate() {
		decimal subTotal = 0m;
	
		// Total up line items
		foreach (OrderLineItem lineItem in OrderLineItems) { 
			subTotal += lineItem.Price;
		}
	
		// Subtract Discounts
		foreach (decimal discount in Discounts)
			subTotal -= discount;
			
		// Calculate Tax
		decimal tax = subTotal * Tax;
		// Calculate GrandTotal
		decimal grandTotal = subTotal + tax;
		return grandTotal;
	}
}
 
 
public class OrderLineItem {
	public decimal Price { get; private set;}
}
public class Order {
	public IEnumerable<OrderLineItem> OrderLineItems { get; private set;}
	public IEnumerable<decimal> Discounts { get; private set; }
	public decimal Tax { get; private set; }
	 public decimal Calculate() {
	 	return new OrderCalculator(this).Calculate();
	 }
}
public class OrderCalculator {
	private decimal SubTotal { get; set;}
	private IEnumerable<OrderLineItem> OrderLineItems { get; set; }
	private IEnumerable<decimal> Discounts { get; set; }
	private decimal Tax { get; set; }
	public OrderCalculator(Order order) {
		OrderLineItems = order.OrderLineItems;
		Discounts = order.Discounts;
		Tax = order.Tax;
	}
	public decimal Calculate() {
		CalculateSubTotal(); 
		SubtractDiscounts();
		CalculateTax();
		return SubTotal;
	}
	private void CalculateSubTotal() {
		// Total up line items
		foreach (OrderLineItem lineItem in OrderLineItems)
			SubTotal += lineItem.Price;
		}
	private void SubtractDiscounts() {
		// Subtract Discounts
		foreach (decimal discount in Discounts)
			SubTotal -= discount;
		}
	private void CalculateTax() {
		// Calculate Tax
		SubTotal += SubTotal * Tax;
	}
} 
```
