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
 1: public class OrderLineItem
 2: {
 3: public decimal Price { get; private set; }
 4: }
 5:
 6: public class Order
 7: {
 8: private IList<OrderLineItem> OrderLineItems { get; set; }
 9: private IList<decimal> Discounts { get; set; }
 10: private decimal Tax { get; set; }
 11:
 12: public decimal Calculate()
 13: {
 14: decimal subTotal = 0m;
 15:
 16: // Total up line items
 17: foreach (OrderLineItem lineItem in OrderLineItems)
 18: {
 19: subTotal += lineItem.Price;
 20: }
 21:
 22: // Subtract Discounts
 23: foreach (decimal discount in Discounts)
 24: subTotal -= discount;
 25:
 26: // Calculate Tax
 27: decimal tax = subTotal * Tax;
 28:
 29: // Calculate GrandTotal
 30: decimal grandTotal = subTotal + tax;
 31:
 32: return grandTotal;
 33: }
 34: }
 
 
 1: public class OrderLineItem
 2: {
 3: public decimal Price { get; private set;}
 4: }
 5:
 6: public class Order
 7: {
 8: public IEnumerable<OrderLineItem> OrderLineItems { get; private set;}
 9: public IEnumerable<decimal> Discounts { get; private set; }
 10: public decimal Tax { get; private set; } 
 11:
 12: public decimal Calculate()
 13: {
 14: return new OrderCalculator(this).Calculate();
 15: }
 16: }
 17:
 18: public class OrderCalculator
 19: {
 20: private decimal SubTotal { get; set;}
 21: private IEnumerable<OrderLineItem> OrderLineItems { get; set; }
 22: private IEnumerable<decimal> Discounts { get; set; }
 23: private decimal Tax { get; set; }
 24:
 25: public OrderCalculator(Order order)
 26: {
 27: OrderLineItems = order.OrderLineItems;
 28: Discounts = order.Discounts;
 29: Tax = order.Tax;
 30: }
 31:
 32: public decimal Calculate()
 33: {
 34: CalculateSubTotal();
 35:
 36: SubtractDiscounts();
 37:
 38: CalculateTax();
 39:
 40: return SubTotal;
 41: }
 42:
 43: private void CalculateSubTotal()
 44: {
 45: // Total up line items
 46: foreach (OrderLineItem lineItem in OrderLineItems)
 47: SubTotal += lineItem.Price;
 48: }
 49:
 50: private void SubtractDiscounts()
 51: {
 52: // Subtract Discounts
 53: foreach (decimal discount in Discounts)
 54: SubTotal -= discount;
 55: }
 56:
 57: private void CalculateTax()
 58: {
 59: // Calculate Tax
 60: SubTotal += SubTotal * Tax;
 61: }
 62: } 
 
