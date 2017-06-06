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
	public int CalculateShippingAmount(State shipToState) {
		switch (shipToState) {
		case Alaska:
			return GetAlaskaShippingAmount();
		case NewYork:
			return GetNewYorkShippingAmount();
		case Florida:
			return GetFloridaShippingAmount();
		default:
			return 0;
		}
	}
	private int GetFloridaShippingAmount() {
		return 15;
	}
	private int GetNewYorkShippingAmount() {
		return 10;
	}
	private int GetAlaskaShippingAmount() {
		return 3;
	}
}

public class Client {
	public int calculateShipping(){
		ShippingInfo shippingInfo = new ShippingInfo();
		return shippingInfo.CalculateShippingAmount(State.Alaska);
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


```
 1: namespace LosTechies.DaysOfRefactoring.SwitchToStrategy.Before
 2: {
 3: public class ClientCode
 4: {
 5: public decimal CalculateShipping()
 6: {
 7: ShippingInfo shippingInfo = new ShippingInfo();
 8: return shippingInfo.CalculateShippingAmount(State.Alaska);
 9: }
 10: }
 11:
 12: public enum State
 13: {
 14: Alaska,
 15: NewYork,
 16: Florida
 17: }
 18:
 19: public class ShippingInfo
 20: {
 21: public decimal CalculateShippingAmount(State shipToState)
 22: {
 23: switch(shipToState)
 24: {
 25: case State.Alaska:
 26: return GetAlaskaShippingAmount();
 27: case State.NewYork:
 28: return GetNewYorkShippingAmount();
 29: case State.Florida:
 30: return GetFloridaShippingAmount();
 31: default:
 32: return 0m;
 33: }
 34: }
 35:
 36: private decimal GetAlaskaShippingAmount()
 37: {
 38: return 15m;
 39: }
 40:
 41: private decimal GetNewYorkShippingAmount()
 42: {
 43: return 10m;
 44: }
 45:
 46: private decimal GetFloridaShippingAmount()
 47: {
 48: return 3m;
 49: }
 50: }
 51: } 
 
 1: using System.Collections.Generic;
 2:
 3: namespace LosTechies.DaysOfRefactoring.SwitchToStrategy.After
 4: {
 5: public class ClientCode
 6: {
 7: public decimal CalculateShipping()
 8: {
 9: ShippingInfo shippingInfo = new ShippingInfo();
 10: return shippingInfo.CalculateShippingAmount(State.Alaska);
 11: }
 12: }
 13:
 14: public enum State
 15: {
 16: Alaska,
 17: NewYork,
 18: Florida
 19: }
 20:
 21: public class ShippingInfo
 22: {
 23: private IDictionary<State, IShippingCalculation> ShippingCalculations { get; set; }
 24:
 25: public ShippingInfo()
 26: {
 27: ShippingCalculations = new Dictionary<State, IShippingCalculation>
 28: {
 29: { State.Alaska, new AlaskShippingCalculation() },
 30: { State.NewYork, new NewYorkShippingCalculation() },
 31: { State.Florida, new FloridaShippingCalculation() }
 32: };
 33: }
 34:
 35: public decimal CalculateShippingAmount(State shipToState)
 36: {
 37: return ShippingCalculations[shipToState].Calculate();
 38: }
 39: }
 40:
 41: public interface IShippingCalculation
 42: {
 43: decimal Calculate();
 44: }
 45:
 46: public class AlaskShippingCalculation : IShippingCalculation
 47: { 
 48: public decimal Calculate()
 49: {
 50: return 15m;
 51: }
 52: }
 53:
 54: public class NewYorkShippingCalculation : IShippingCalculation
 55: {
 56: public decimal Calculate()
 57: {
 58: return 10m;
 59: }
 60: }
 61:
 62: public class FloridaShippingCalculation : IShippingCalculation
 63: {
 64: public decimal Calculate()
 65: {
 66: return 3m;
 67: }
 68: }
 69: }
``` 
To take this sample full circle, Here is how you would wire up your bindings if you were using Ninject as
your IoC container in the ShippingInfo constructor. Quite a few things changed here, mainly the enum for
the state now lives in the strategy and ninject gives us a IEnumerable of all bindings to the constructor of
IShippingInfo. We then create a dictionary using the state property on the strategy to populate our
dictionary and the rest is the same. (thanks to Nate Kohari and Jayme Davis)
```
 1: public interface IShippingInfo
 2: {
 3: decimal CalculateShippingAmount(State state);
 4: }
 5:
 6: public class ClientCode
 7: {
 8: [Inject]
 9: public IShippingInfo ShippingInfo { get; set; }
 10:
 11: public decimal CalculateShipping()
 12: {
 13: return ShippingInfo.CalculateShippingAmount(State.Alaska);
 14: }
 15: }
 16:
 17: public enum State
 18: {
 19: Alaska,
 20: NewYork,
 21: Florida
 22: }
 23:
 24: public class ShippingInfo : IShippingInfo
 25: {
 26: private IDictionary<State, IShippingCalculation> ShippingCalculations { get; set; }
 27:
 28: public ShippingInfo(IEnumerable<IShippingCalculation> shippingCalculations)
 29: {
 30: ShippingCalculations = shippingCalculations.ToDictionary(calc => calc.State);
 31: }
 32:
 33: public decimal CalculateShippingAmount(State shipToState)
 34: {
 35: return ShippingCalculations[shipToState].Calculate();
 36: }
 37: }
 38:
 39: public interface IShippingCalculation
 40: {
 41: State State { get; }
 42: decimal Calculate();
 43: }
 44:
 45: public class AlaskShippingCalculation : IShippingCalculation
 46: {
 47: public State State { get { return State.Alaska; } }
 48:
 49: public decimal Calculate()
 50: {
 51: return 15m;
 52: }
 53: }
 54:
 55: public class NewYorkShippingCalculation : IShippingCalculation
 56: {
 57: public State State { get { return State.NewYork; } }
 58:
 59: public decimal Calculate()
 60: {
 61: return 10m;
 62: }
 63: }
 64:
 65: public class FloridaShippingCalculation : IShippingCalculation
 66: {
 67: public State State { get { return State.Florida; } }
 68:
 69: public decimal Calculate()
 70: {
 71: return 3m;
 72: }
 73: }
 ```