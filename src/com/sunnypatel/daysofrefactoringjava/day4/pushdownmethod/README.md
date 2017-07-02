Yesterday we looked at the pull up refactoring to move a method to a base class so mutiple derived classes can use a method. Today we look at the opposite. Here is the code before the refactoring:
```Java
public class Animal {
	public void bark(){
		System.out.println("Bark!");
	}
	public void walk(){
		System.out.println("WALKING!");
	}
}

public class Cat extends Animal{
	//Contains a bark method which isnot needed!
}

public class Dog extends Animal {
}
```


So here we have some code with a base class that has a Bark method. Perhaps at one time our cat could bark, but now we no longer need that functionality on the Cat class. So we “Push Down” the Bark method into the Dog class as it is no longer needed on the base class but perhaps it is still needed when dealing explicitly with a Dog. At this time, it’s worthwhile to evaluate if there is any behavior still located on the Animal base class. If not, it is a good opportunity to turn the Animal abstract class into an interface instead as no code is required on the contract and can be treated as a marker interface.

```Java
public abstract class Animal {
	public void walk(){
		System.out.println("WALKING!");
	}
	//No bark method needed here!
}
public class Cat extends Animal {
	public void meow() {
		System.out.println("MEOW!");
	}
}

public class Dog extends Animal {
	public void bark() {
		System.out.println("Bark!");
	}

}
```

#### Original C# code from the book:
```cs
public abstract class Animal
{
    public void Bark()
    {
        // code to bark
    }
}

public class Dog : Animal
{
}

public class Cat : Animal
{
}
```

```cs
public abstract class Animal
{
}

public class Dog : Animal
{
    public void Bark()
    {
        // code to bark
    }
}

public class Cat : Animal
{
}
```
