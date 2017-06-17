Today's refactoring doesn't come from any place specifically, just something I've picked up over time that I
find myself using often. Any variations/comments would be appreciated to this approach. 
I think there's some other good refactorings around these type of problems.
A common code smell that I come across from time to time is using exceptions to control program flow.
You may see something to this effect:
```Java

public interface MicrowaveMotor {
	public void cook(String dish);
}

public class InUseException extends RuntimeException{

	private static final long serialVersionUID = -7115741647066213175L;
	public InUseException(String message){
		super(message);
	}
}

public class Microwave {
	private MicrowaveMotor motor;
	public boolean cook(String dish){
		boolean cooked = false;
		try{
			motor.cook(dish);
			cooked = true;
		}
		catch(InUseException e){
			cooked = false;
		}
		return cooked;
	}
}
```

Exceptions should only be there to do exactly what they are for, handle exceptional behavior. Most of the
time you can replace this type of code with a proper conditional and handle it properly. This is called design
by contract in the after example because we are ensuring a specific state of the Motor class before
performing the necessary work instead of letting an exception handle it.

```Java
public interface MicrowaveMotor {
	public void cook(String dish);
	public boolean inUse();
}

public class Microwave {
	private MicrowaveMotor motor;

	public boolean cook(String dish) {
		if (motor.inUse())
			return false;
		else
			motor.cook(dish);
		return true;
	}
}
```
 
 ##Original C# Code from the book:
 ```cs
public class Microwave {
	private IMicrowaveMotor Motor { get; set;}
	public bool Start(object food) {
 		bool foodCooked = false;
 		try{
  			Motor.Cook(food);
  			foodCooked = true;
  		}
  		catch(InUseException){
  			foodcooked = false;
  		}
  		return foodCooked;
  	}
}

public class Microwave {
	private IMicrowaveMotor Motor { get; set; }
	public bool Start(object food) {
 		if (Motor.IsInUse)
 			return false;

  		Motor.Cook(food);
  		return true;
  	}
} 
```
