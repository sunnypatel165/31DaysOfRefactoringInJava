Today we look at the Extract Method refactoring. This is an extremely easy refactoring with several benefits. First, it helps to make code more readable by placing logic behind descriptive method names. This reduces the amount of investigation the next developer needs to do as a method name can describe what a portion of code is doing. This in turn reduces bugs in the code because less assumptions need to be made. Here’s some code before we apply the refactoring:

```Java
public class Receipt {
	private List<Integer> discounts = new ArrayList<>();
	private List<Integer> itemTotals = new ArrayList<>();

	private double subTotal = 0.0;
	
	public double calculateGrandTotal(){
		itemTotals.forEach(item -> subTotal += item);
		
		if(discounts.size() > 0 )
			discounts.forEach(discount -> subTotal -= discount);
		
		double tax = subTotal * 0.065;
		
		subTotal = subTotal + tax;
		return subTotal;
	}
}
```

You can see that the CalculateGrandTotal method is actually doing three different things here. It’s calculating the subtotal, applying any discounts and then calculating the tax for the receipt. Instead of making a developer look through that whole method to determine what each thing is doing, it would save time and readability to seperate those distinct tasks into their own methods like so:

```Java
public class Receipt {
	private List<Integer> discounts = new ArrayList<>();
	private List<Integer> itemTotals = new ArrayList<>();

	public double CalculateGrandTotal()
    {
         double subTotal = calculateItemsTotal();
         subTotal = calculateDiscounts(subTotal);
         subTotal = calculateTax(subTotal);
         return subTotal;
     }
	private double calculateItemsTotal(){
		double subTotal=0.0;
		for(Integer item : itemTotals)
			subTotal += item;
		return subTotal;
	}
	private double calculateDiscounts(double subTotal){
		for(Integer discount: discounts)
			subTotal -= discount;
		return subTotal;
	}
	private double calculateTax(double subTotal){
		return subTotal + 0.65 * subTotal;
	}
}
```
This refactoring comes from Martin Fowler and can be found at: https://refactoring.com/catalog/extractMethod.html

#### Original C# code from the book:

```
   1: public class Receipt
   2: {
   3:     private IList<decimal> Discounts { get; set; }
   4:     private IList<decimal> ItemTotals { get; set; }
   5:  
   6:     public decimal CalculateGrandTotal()
   7:     {
   8:         decimal subTotal = 0m;
   9:         foreach (decimal itemTotal in ItemTotals)
  10:             subTotal += itemTotal;
  11:  
  12:         if (Discounts.Count > 0)
  13:         {
  14:             foreach (decimal discount in Discounts)
  15:                 subTotal -= discount;
  16:         }
  17:  
  18:         decimal tax = subTotal * 0.065m;
  19:  
  20:         subTotal += tax;
  21:  
  22:         return subTotal;
  23:     }
  24: }
  
   1: public class Receipt
   2: {
   3:     private IList<decimal> Discounts { get; set; }
   4:     private IList<decimal> ItemTotals { get; set; }
   5:  
   6:     public decimal CalculateGrandTotal()
   7:     {
   8:         decimal subTotal = CalculateSubTotal();
   9:  
  10:         subTotal = CalculateDiscounts(subTotal);
  11:  
  12:         subTotal = CalculateTax(subTotal);
  13:  
  14:         return subTotal;
  15:     }
  16:  
  17:     private decimal CalculateTax(decimal subTotal)
  18:     {
  19:         decimal tax = subTotal * 0.065m;
  20:  
  21:         subTotal += tax;
  22:         return subTotal;
  23:     }
  24:  
  25:     private decimal CalculateDiscounts(decimal subTotal)
  26:     {
  27:         if (Discounts.Count > 0)
  28:         {
  29:             foreach (decimal discount in Discounts)
  30:                 subTotal -= discount;
  31:         }
  32:         return subTotal;
  33:     }
  34:  
  35:     private decimal CalculateSubTotal()
  36:     {
  37:         decimal subTotal = 0m;
  38:         foreach (decimal itemTotal in ItemTotals)
  39:             subTotal += itemTotal;
  40:         return subTotal;
  41:     }
  42: }
