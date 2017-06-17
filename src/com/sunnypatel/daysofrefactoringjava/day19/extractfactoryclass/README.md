Todays refactoring was first coined by the GangOfFour and has many resources on the web that have
different usages of this pattern. 
Two different aims of the factory pattern can be found on the GoF website here and here.

Often in code some involved setup of objects is required in order to get them into a state where we can begin working with them. 
Usually this setup is nothing more than creating a new instance of the object and working with it in whatever manner we need. 
Sometimes however the creation requirements of this object may grow and clouds the original code that was used to create the object. 
This is where a Factory class comes into play. 
For a full description of the factory pattern you can read more here. 
On the complex endof the factory pattern is for creating families of objects using Abstract Factory. 
Our usage is on the basic end where we have one factory class creating one specific instance for us. Take a look at the code before:

```Java
public class PoliceCar {
	private int mileage;
	private boolean serviceRequired;
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public boolean isServiceRequired() {
		return serviceRequired;
	}
	public void setServiceRequired(boolean serviceRequired) {
		this.serviceRequired = serviceRequired;
	}
}

public class PoliceCarController {
	public PoliceCar createPoliceCar(int mileage, boolean serviceRequired){
		PoliceCar car = new PoliceCar();
		car.setMileage(mileage);
		car.setServiceRequired(serviceRequired);
		//and some other logic as the system grows
		return car;	
	}
}
```
As we can see, the new action is responsible for creating a PoliceCar and then setting some initial properties on the police car depending on some external input. This works fine for simple setup, but over time this can grow and the burden of creating the police car is put on the controller which isn’t really
something that the controller should be tasked with.
In this instance we can extract our creation code and place in a Factory class that has the distinct responsibility of create instances of PoliceCar’s.

```Java
public class PoliceCar {
	private int mileage;
	private boolean serviceRequired;
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public boolean isServiceRequired() {
		return serviceRequired;
	}
	public void setServiceRequired(boolean serviceRequired) {
		this.serviceRequired = serviceRequired;
	}
}

public interface PoliceCarFactory {
	public PoliceCar create(int mileage, boolean serviceRequired);
}

public class PoliceCarFactoryImpl implements PoliceCarFactory {

	@Override
	public PoliceCar create(int mileage, boolean serviceRequired) {
		PoliceCar car = new PoliceCar();
		car.setMileage(mileage);
		car.setServiceRequired(serviceRequired);
		// and some other logic as the system grows

		return car;
	}
}

public class PoliceCarController {
	private PoliceCarFactory policeCarFactory;
	public PoliceCarController(PoliceCarFactory policeCarFactory){
		this.policeCarFactory = policeCarFactory;
	}
	public PoliceCar createPoliceCar(int mileage, boolean serviceRequired){
		return policeCarFactory.create(mileage, serviceRequired);
	}

}
```
Now that we have the creation logic put off to a factory, we can add to that one class that is tasked with
creating instances for us without the worry of missing something during setup or duplicating code.


#### Original C# code from the book:
```cs
public class PoliceCarController {
	public PoliceCar New(int mileage, bool serviceRequired) {
		PoliceCar policeCar = new PoliceCar();
		policeCar.ServiceRequired = serviceRequired;
		policeCar.Mileage = mileage;
		return policeCar;
	}
}
//solution:
public interface IPoliceCarFactory {
	PoliceCar Create(int mileage, bool serviceRequired);
}

public class PoliceCarFactory : IPoliceCarFactory {
	public PoliceCar Create(int mileage, bool serviceRequired) {
		PoliceCar policeCar = new PoliceCar();
		policeCar.ReadForService = serviceRequired;
		policeCar.Mileage = mileage;
		return policeCar;
	}
}

public class PoliceCarController {
	public IPoliceCarFactory PoliceCarFactory { get; set; }
	
	public PoliceCarController(IPoliceCarFactory policeCarFactory){
		PoliceCarFactory = policeCarFactory;
	}
	
	public PoliceCar New(int mileage, bool serviceRequired) {
		return PoliceCarFactory.Create(mileage, serviceRequired);
	}
}
```
 