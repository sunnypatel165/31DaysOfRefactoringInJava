# Refactoring Day 3 : Pull Up Method
The Pull Up Method refactoring is the process of taking a method and “Pulling” it up in the inheritance
chain. This is used when a method needs to be used by multiple implementers.

```Java
public abstract class Vehicle {
	//methods
}

public class Car extends Vehicle{
	public void turn(Direction direction){
		//some logic
	}
}

public enum Direction {
	Left,
	Right;
}

public class MotorCycle {
	//some code
}

```

As you can see, our Turn method is currently only available to the car class, we also want to use it in the
motorcycle class so we create a base class if one doesn’t already exist and “pull up” the method into the
base class making it available to both. The only drawback is we have increased surface area of the base
class adding to it’s complexity so use wisely. Only place methods that need to be used by more that one
derived class. Once you start overusing inheritance it breaks down pretty quickly and you should start to
lean towards composition over inheritance. Here is the code after the refactoring:

```Java
public enum Direction {
	Left,
	Right;
}

public abstract class Vehicle {
	public abstract void turn(Direction direction);
	//methods
}

public class Car extends Vehicle{
	public void turn(Direction direction){
		//Add the logic of car turning.
	}
}

public class MotorCycle extends Vehicle{
	@Override
	public void turn(Direction direction) {
		//add the logic of MotorCycle turn
	}
}
```

#### Original C# Code from the book:
```cs
public abstract class Vehicle
{
	// other methods
}

public class Car : Vehicle
{
	public void Turn(Direction direction)
	{
		// code here
	}
}

public class Motorcycle : Vehicle
{
}

public enum Direction
{
	Left,
	Right
}
```

```cs
public abstract class Vehicle
{
	public void Turn(Direction direction)
	{
		// code here
	}
}

public class Car : Vehicle
{
}

public class Motorcycle : Vehicle
{
}

public enum Direction
{
	Left,
	Right
}
```
