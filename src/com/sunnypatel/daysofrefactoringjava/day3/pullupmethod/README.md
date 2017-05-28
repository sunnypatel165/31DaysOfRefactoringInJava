# Refactoring Day 3 : Pull Up Method
The Pull Up Method refactoring is the process of taking a method and “Pulling” it up in the inheritance
chain. This is used when a method needs to be used by multiple implementers.
``` Java
 	1: public abstract class Vehicle
 	2: {
 	3: 		// other methods
 	4: }
 	5:
 	6: public class Car : Vehicle
 	7: {
 	8: 		public void Turn(Direction direction)
 	9: 		{
 	10: 		// code here
 	11: 	}
 	12: }
 	13:
 	14: public class Motorcycle : Vehicle
 	15: {
 	16: }
 	17:
 	18: public enum Direction
 	19: {
 	20: 	Left,
 	21: 	Right
 	22: }
 	```
As you can see, our Turn method is currently only available to the car class, we also want to use it in the
motorcycle class so we create a base class if one doesn’t already exist and “pull up” the method into the
base class making it available to both. The only drawback is we have increased surface area of the base
class adding to it’s complexity so use wisely. Only place methods that need to be used by more that one
derived class. Once you start overusing inheritance it breaks down pretty quickly and you should start to
lean towards composition over inheritance. Here is the code after the refactoring:
```Java
 	1: public abstract class Vehicle
 	2: {
 	3: 		public void Turn(Direction direction)
 	4: 		{
 	5: 			// code here
 	6: 		}
 	7: }
 	8:
 	9: public class Car : Vehicle
 	10: {
 	11: }
 	12:
 	13: public class Motorcycle : Vehicle
 	14: {
 	15: }
 	16:
 	17: public enum Direction
 	18: {
 	19: 	Left,
 	20: 	Right
 	21: } 
 	```