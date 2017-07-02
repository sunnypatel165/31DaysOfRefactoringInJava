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

```cs
public class Receipt
{
    private IList<decimal> Discounts { get; set; }
    private IList<decimal> ItemTotals { get; set; }

    public decimal CalculateGrandTotal()
    {
        decimal subTotal = 0m;
        foreach (decimal itemTotal in ItemTotals)
            subTotal += itemTotal;

        if (Discounts.Count > 0)
        {
            foreach (decimal discount in Discounts)
                subTotal -= discount;
        }

        decimal tax = subTotal * 0.065m;

        subTotal += tax;

        return subTotal;
    }
}
```

```cs  
public class Receipt
{
    private IList<decimal> Discounts { get; set; }
    private IList<decimal> ItemTotals { get; set; }

    public decimal CalculateGrandTotal()
    {
        decimal subTotal = CalculateSubTotal();

        subTotal = CalculateDiscounts(subTotal);

        subTotal = CalculateTax(subTotal);

        return subTotal;
    }

    private decimal CalculateTax(decimal subTotal)
    {
        decimal tax = subTotal * 0.065m;

        subTotal += tax;
        return subTotal;
    }

    private decimal CalculateDiscounts(decimal subTotal)
    {
        if (Discounts.Count > 0)
        {
            foreach (decimal discount in Discounts)
                subTotal -= discount;
        }
        return subTotal;
    }

    private decimal CalculateSubTotal()
    {
        decimal subTotal = 0m;
        foreach (decimal itemTotal in ItemTotals)
            subTotal += itemTotal;
        return subTotal;
    }
}
```
