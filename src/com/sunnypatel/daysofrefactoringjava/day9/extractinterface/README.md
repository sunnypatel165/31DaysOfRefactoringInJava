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

   1: public class ClassRegistration
   2: {
   3:     public void Create()
   4:     {
   5:         // create registration code
   6:     }
   7:  
   8:     public void Transfer()
   9:     {
  10:         // class transfer code
  11:     }
  12:  
  13:     public decimal Total { get; private set; }
  14: }
  15:  
  16: public class RegistrationProcessor
  17: {
  18:     public decimal ProcessRegistration(ClassRegistration registration)
  19:     {
  20:         registration.Create();
  21:         return registration.Total;
  22:     }
  23: }
  
   1: public interface IClassRegistration
   2: {
   3:     void Create();
   4:     decimal Total { get; }
   5: }
   6:  
   7: public class ClassRegistration : IClassRegistration
   8: {
   9:     public void Create()
  10:     {
  11:         // create registration code
  12:     }
  13:  
  14:     public void Transfer()
  15:     {
  16:         // class transfer code
  17:     }
  18:  
  19:     public decimal Total { get; private set; }
  20: }
  21:  
  22: public class RegistrationProcessor
  23: {
  24:     public decimal ProcessRegistration(IClassRegistration registration)
  25:     {
  26:         registration.Create();
  27:         return registration.Total;
  28:     }
  29: }
```