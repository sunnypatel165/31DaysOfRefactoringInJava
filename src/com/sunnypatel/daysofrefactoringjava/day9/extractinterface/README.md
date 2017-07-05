Today we look at an often overlooked refactoring. Extract Interface. When you notice more than one class using a similar subset of methods on a class, it is useful to break the dependency and introduce an interface that the consumers to use. It’s easy to implement and has benefits from loose coupling.

```Java
public class ClassRegistration {
	public void create(){
		//creation code
	}
	public void transfer(){
		//transfer code
	}
	private int total;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
public class RegistrationProcessor {
	public int processRegistration(ClassRegistration registration){
		registration.create();
		return registration.getTotal();
	}
}
```   
In the after example, you can see we extracted the methods that both consumers use and placed them in an interface. Now the consumers don’t care/know about the class that is implementing these methods. We have decoupled our consumer from the actual implementation and depend only on the contract that we have created.

```Java
public interface ClassRegistration {
	public void create();
	public int getTotal();

}
public class ClassRegistrationImpl implements ClassRegistration {

	@Override
	public void create() {
		//creation code
	}

	private int total;
	@Override
	public int getTotal() {
		return total;
	}
}

public class RegistrationProcessor {
	public int processRegistration(ClassRegistration registration){
		registration.create();
		return registration.getTotal();
	}

}
```

This refactoring was first published by Martin Fowler and can be found in his list of refactorings [here](http://refactoring.com/catalog/extractInterface.html).

#### Original C# code from the book

```cs
public class ClassRegistration
{
    public void Create()
    {
        // create registration code
    }

    public void Transfer()
    {
        // class transfer code
    }

    public decimal Total { get; private set; }
}

public class RegistrationProcessor
{
    public decimal ProcessRegistration(ClassRegistration registration)
    {
        registration.Create();
        return registration.Total;
    }
}
```

```cs
public interface IClassRegistration
{
    void Create();
    decimal Total { get; }
}

public class ClassRegistration : IClassRegistration
{
    public void Create()
    {
        // create registration code
    }

    public void Transfer()
    {
        // class transfer code
    }

    public decimal Total { get; private set; }
}

public class RegistrationProcessor
{
    public decimal ProcessRegistration(IClassRegistration registration)
    {
        registration.Create();
        return registration.Total;
    }
}
```
