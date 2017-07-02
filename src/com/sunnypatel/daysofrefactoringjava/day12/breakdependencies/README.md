Today's refactoring is useful if you are trying to introduce unit tests into your code base as testing “seams” are needed to properly mock/isolate areas you don’t wish to test.
In this example we have client code that is using a static class to accomplish some work. The problem with this when it comes to unit testing because there is no way to mock the static class from our unit test.
To work around this you can apply a wrapper interface around the static to create a seam and break the dependency on the static.

```Java
public class Feeder {
	public static void replenishFood(){
		//fill up the bowl
	}
}

public class AnimalFeedingService {
	private boolean isBowlEmpty;

	public void feed(){
		if(isBowlEmpty){
			Feeder.replenishFood();
		}
	}

	public boolean isBowlEmpty() {
		return isBowlEmpty;
	}
	public void setBowlEmpty(boolean isBowlEmpty) {
		this.isBowlEmpty = isBowlEmpty;
	}
}
```

All we did to apply this refactoring was introduce an interface and class that simply calls the underlying
static class. So the behavior is still the same, just the manner in which it is invoked has changed. This is
good to get a starting point to begin refactoring from and an easy way to add unit tests to your code base.

 ```Java
public interface FeederService {
	void replenishFood();
}

public class FeederServiceImpl implements FeederService{

	@Override
	public void replenishFood() {
		Feeder.replenishFood();
	}
}

public class Feeder {
	public static void replenishFood() {
		// fill up the bowl
	}
}

public class AnimalFeedingService {
	private FeederService feederService;
	public boolean isBowlEmpty;

	public AnimalFeedingService(FeederService feederService) {
		this.feederService = feederService;
	}

	public void feed() {
		if (isBowlEmpty) {
			feederService.replenishFood();
		}
	}

	public FeederService getFeederService() {
		return feederService;
	}

	public void setFeederService(FeederService feederService) {
		this.feederService = feederService;
	}

	public boolean isBowlEmpty() {
		return isBowlEmpty;
	}

	public void setBowlEmpty(boolean isBowlEmpty) {
		this.isBowlEmpty = isBowlEmpty;
	}
}


 ```

 We can now mock IFeederService during our unit test via the AnimalFeedingService constructor by passing
in a mock of IFeederService. Later we can move the code in the static into FeederService and delete the
static class completely once we have some tests in place.

#### Original C# code from the book:

```cs
public class AnimalFeedingService
{
	private bool FoodBowlEmpty { get; set; }

	public void Feed()
	{
		if (FoodBowlEmpty)
		    Feeder.ReplenishFood();

		// more code to feed the animal
	}
}

public static class Feeder
{
	public static void ReplenishFood()
	{
		// fill up bowl
	}
}
public class AnimalFeedingService
{
	private bool FoodBowlEmpty { get; set; }

	public void Feed()
	{
		if (FoodBowlEmpty)
		    Feeder.ReplenishFood();

		// more code to feed the animal
	}
}

public static class Feeder
{
	public static void ReplenishFood()
	{
		// fill up bowl
	}
}
```

```cs
public class AnimalFeedingService
{
	public IFeederService FeederService { get; set; }

	public AnimalFeedingService(IFeederService feederService)
	{
		FeederService = feederService;
	}

	private bool FoodBowlEmpty { get; set; }

	public void Feed()
	{
		if (FoodBowlEmpty)
		    FeederService.ReplenishFood();

		// more code to feed the animal
	}
}

public interface IFeederService
{
	void ReplenishFood();
}

public class FeederService : IFeederService
{
	public void ReplenishFood()
	{
		Feeder.ReplenishFood();
	}
}

public static class Feeder
{
	public static void ReplenishFood()
	{
		// fill up bowl
	}
}
```
