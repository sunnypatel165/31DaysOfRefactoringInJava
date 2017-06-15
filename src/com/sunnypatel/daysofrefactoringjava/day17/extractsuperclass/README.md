Today's refactoring is from Martin Fowler's refactoring catalog.
This refactoring is used quite often when you have a number of methods that you want to “pull up” into a
base class to allow other classes in the same hierarchy to use. Here is a class that uses two methods that we
want to extract and make available to other classes.

```Java
public class Dog {
	public void eat() {
		// eat code
	}

	public void groom() {
		// groom code
	}
}
```
 
After applying the refactoring we just move the required methods into a new base class. This is very similar
to the [pull up refactoring], except that you would apply this refactoring when a base class doesn’t already
exist.

```Java
public class Animal {
	public void eat() {
		// eat code
	}

	public void groom() {
		// groom code
	}
}
public class Dog extends Animal{

}
```

 #### Original C# code from the book:
```cs
public class Dog {

	public void EatFood(){
	// eat some food
	}
	public void Groom() {
		// perform grooming
	}
}

public class Animal {

	public void EatFood() {
		// eat some food
	}

	public void Groom() {
		// perform grooming
	}
}

public class Dog:Animal {

}
```
