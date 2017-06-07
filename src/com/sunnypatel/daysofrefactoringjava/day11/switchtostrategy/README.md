Todays refactoring doesn't come from any one source, rather I've used different versions over the years
and I'm sure other have different variations of the same aim.
This refactoring is used when you have a larger switch statement that continually changes because of new
conditions being added. In these cases it’s often better to introduce the strategy pattern and encapsulate
each condition in it’s own class. The strategy refactoring I’m showing here is refactoring towards a
dictionary strategy. There is several ways to implement the strategy pattern, the benefit of using this
method is that consumers needn’t change after applying this refactoring.

```Java
public enum State {
	Alaska, NewYork, Florida
}

public class ShippingInfo {
	public int calculateShippingAmount(State shipToState) {
		switch (shipToState) {
			case Alaska:
				return getAlaskaShippingAmount();
			case NewYork:
				return getNewYorkShippingAmount();
			case Florida:
				return getFloridaShippingAmount();
			default:
				return 0;
		}
	}

	private int getFloridaShippingAmount() {
		return 15;
	}

	private int getNewYorkShippingAmount() {
		return 10;
	}

	private int getAlaskaShippingAmount() {
		return 3;
	}

}

public class Client {
	public int calculateShipping() {
		ShippingInfo shippingInfo = new ShippingInfo();
		return shippingInfo.calculateShippingAmount(State.Alaska);
	}
}
```

To apply this refactoring take the condition that is being tested and place it in it’s own class that adheres to
a common interface. Then by passing the enum as the dictionary key, we can select the proper
implementation and execute the code at hand. In the future when you want to add another condition, add
another implementation and add the implementation to the ShippingCalculations dictionary. As I stated
before, this is not the only option to implement the strategy pattern. I bold that because I know someone
will bring this up in the comments :) Use what works for you. The benefit of doing this refactoring in this
manner is that none of your client code will need to change. All of the modifications exist within the
ShippingInfo class.

```Java
public enum State {
	Alaska, NewYork, Florida
}

public interface ShippingCalculator {
	public int calculate();
}

public class NewYorkShippingCalculator implements ShippingCalculator {

	@Override
	public int calculate() {
		return 10;
	}
}

public class FloridaShippingCalculator implements ShippingCalculator {

	@Override
	public int calculate() {
		return 3;
	}
}

public class AlaskaShippingCalculator implements ShippingCalculator {

	@Override
	public int calculate() {
		return 15;
	}
}

public class ShippingInfo {
	private Map<State, ShippingCalculator> shippingCalculations;

	public ShippingInfo(){
		shippingCalculations = new HashMap<>();
		shippingCalculations.put(State.Alaska, new AlaskaShippingCalculator());
		shippingCalculations.put(State.NewYork, new NewYorkShippingCalculator());
		shippingCalculations.put(State.Florida, new FloridaShippingCalculator());
	}
	public int calculateShippingCost(State state){
		return shippingCalculations.get(state).calculate();
	}
	public Map<State, ShippingCalculator> getShippingCalculations() {
		return shippingCalculations;
	}
	public void setShippingCalculations(Map<State, ShippingCalculator> shippingCalculations) {
		this.shippingCalculations = shippingCalculations;
	}
}
```

Jayme Davis pointed out that doing this refactoring really only creates more classes because the binding still
needs to be done via the ctor, but would be more beneficial if the binding of your IShippingCalculation
strategies can be placed into IoC and that allows you to wire up strategies more easily.


#### Original C# Code from the book


```cs
namespace LosTechies.DaysOfRefactoring.SwitchToStrategy.Before
{
   public class ClientCode
   {
	   public decimal CalculateShipping()
	   {
		   ShippingInfo shippingInfo = new ShippingInfo();
		   return shippingInfo.CalculateShippingAmount(State.Alaska);
	   }
   }

   public enum State
   {
	   Alaska,
	   NewYork,
	   Florida
   }

   public class ShippingInfo
   {
	   public decimal CalculateShippingAmount(State shipToState)
	   {
		   switch(shipToState)
		   {
			   case State.Alaska:
				   return GetAlaskaShippingAmount();
			   case State.NewYork:
				   return GetNewYorkShippingAmount();
			   case State.Florida:
				   return GetFloridaShippingAmount();
			   default:
				   return 0m;
		   }
	   }

	   private decimal GetAlaskaShippingAmount()
	   {
		   return 15m;
	   }

	   private decimal GetNewYorkShippingAmount()
	   {
		   return 10m;
	   }

	   private decimal GetFloridaShippingAmount()
	   {
		   return 3m;
	   }
   }
}

using System.Collections.Generic;

namespace LosTechies.DaysOfRefactoring.SwitchToStrategy.After
{
   public class ClientCode
   {
	   public decimal CalculateShipping()
	   {
		   ShippingInfo shippingInfo = new ShippingInfo();
		   return shippingInfo.CalculateShippingAmount(State.Alaska);
	   }
   }

   public enum State
   {
	   Alaska,
	   NewYork,
	   Florida
   }

   public class ShippingInfo
   {
	   private IDictionary<State, IShippingCalculation> ShippingCalculations { get; set; }

	   public ShippingInfo()
	   {
		   ShippingCalculations = new Dictionary<State, IShippingCalculation>
		   {
			   { State.Alaska, new AlaskShippingCalculation() },
			   { State.NewYork, new NewYorkShippingCalculation() },
			   { State.Florida, new FloridaShippingCalculation() }
		   };
	   }

	   public decimal CalculateShippingAmount(State shipToState)
	   {
		   return ShippingCalculations[shipToState].Calculate();
	   }
   }

   public interface IShippingCalculation
   {
	   decimal Calculate();
   }

   public class AlaskShippingCalculation : IShippingCalculation
   {
	   public decimal Calculate()
	   {
		   return 15m;
	   }
   }

   public class NewYorkShippingCalculation : IShippingCalculation
   {
	   public decimal Calculate()
	   {
		   return 10m;
	   }
   }

   public class FloridaShippingCalculation : IShippingCalculation
   {
	   public decimal Calculate()
	   {
		   return 3m;
	   }
   }
}
```
To take this sample full circle, Here is how you would wire up your bindings if you were using Ninject as
your IoC container in the ShippingInfo constructor. Quite a few things changed here, mainly the enum for
the state now lives in the strategy and ninject gives us a IEnumerable of all bindings to the constructor of
IShippingInfo. We then create a dictionary using the state property on the strategy to populate our
dictionary and the rest is the same. (thanks to Nate Kohari and Jayme Davis)

```cs
public interface IShippingInfo
{
   decimal CalculateShippingAmount(State state);
}

public class ClientCode
{
   [Inject]
   public IShippingInfo ShippingInfo { get; set; }

   public decimal CalculateShipping()
   {
	   return ShippingInfo.CalculateShippingAmount(State.Alaska);
   }
}

public enum State
{
   Alaska,
   NewYork,
   Florida
}

public class ShippingInfo : IShippingInfo
{
   private IDictionary<State, IShippingCalculation> ShippingCalculations { get; set; }

   public ShippingInfo(IEnumerable<IShippingCalculation> shippingCalculations)
   {
	   ShippingCalculations = shippingCalculations.ToDictionary(calc => calc.State);
   }

   public decimal CalculateShippingAmount(State shipToState)
   {
	   return ShippingCalculations[shipToState].Calculate();
   }
}

public interface IShippingCalculation
{
   State State { get; }
   decimal Calculate();
}

public class AlaskShippingCalculation : IShippingCalculation
{
   public State State { get { return State.Alaska; } }

   public decimal Calculate()
   {
	   return 15m;
   }
}

public class NewYorkShippingCalculation : IShippingCalculation
{
   public State State { get { return State.NewYork; } }

   public decimal Calculate()
   {
	   return 10m;
   }
}

public class FloridaShippingCalculation : IShippingCalculation
{
   public State State { get { return State.Florida; } }

   public decimal Calculate()
   {
	   return 3m;
   }
}
```
