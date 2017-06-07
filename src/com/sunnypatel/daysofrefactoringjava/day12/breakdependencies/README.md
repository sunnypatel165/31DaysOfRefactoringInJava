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
```
 1: public class AnimalFeedingService
 2: {
 3: private bool FoodBowlEmpty { get; set; }
 4:
 5: public void Feed()
 6: {
 7: if (FoodBowlEmpty)
 8: Feeder.ReplenishFood();
 9:
 10: // more code to feed the animal
 11: }
 12: }
 13:
 14: public static class Feeder
 15: {
 16: public static void ReplenishFood()
 17: {
 18: // fill up bowl
 19: }
 20: }
 1: public class AnimalFeedingService
 2: {
 3: private bool FoodBowlEmpty { get; set; }
 4:
 5: public void Feed()
 6: {
 7: if (FoodBowlEmpty)
 8: Feeder.ReplenishFood();
 9:
 10: // more code to feed the animal
 11: }
 12: }
 13:
 14: public static class Feeder
 15: {
 16: public static void ReplenishFood()
 17: {
 18: // fill up bowl
 19: }
 20: }
 
 1: public class AnimalFeedingService
 2: {
 3: public IFeederService FeederService { get; set; }
 4:
 5: public AnimalFeedingService(IFeederService feederService)
 6: {
 7: FeederService = feederService;
 8: }
 9:
 10: private bool FoodBowlEmpty { get; set; }
 11:
 12: public void Feed()
 13: {
 14: if (FoodBowlEmpty)
 15: FeederService.ReplenishFood();
 16:
 17: // more code to feed the animal
 18: }
 19: }
 20:
 21: public interface IFeederService
 22: {
 23: void ReplenishFood();
 24: }
 25:
 26: public class FeederService : IFeederService
 27: {
 28: public void ReplenishFood() 
 29: {
 30: Feeder.ReplenishFood();
 31: }
 32: }
 33:
 34: public static class Feeder
 35: {
 36: public static void ReplenishFood()
 37: {
 38: // fill up bowl
 39: }
 40: }
 