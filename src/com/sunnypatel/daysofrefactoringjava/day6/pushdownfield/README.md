Opposite of the Pull Up Field refactoring is push down field. Again, this is a pretty straight forward refactoring without much description needed

```Java
public class Task {
	String resolution;

}
public class BugTask extends Task {

}

public class FeatureTask extends Task{
	//resolution field is not needed here.

}
```

In this example, we have a string field that is only used by one derived class, and thus can be pushed down as no other classes are using it. It’s important to do this refactoring at the moment the base field is no longer used by other derived classes. The longer it sits the more prone it is for someone to simply not touch the field and leave it be.

```Java
public class Task {
}
public class BugTask {
	String resolution;

}
public class FeatureTask {
	String description;
}
```

 #### Original C# code from the book:
 ```
   1: public abstract class Task
   2: {
   3:     protected string _resolution;
   4: }
   5:  
   6: public class BugTask : Task
   7: {
   8: }
   9:  
  10: public class FeatureTask : Task
  11: {
  12: }
  
   1: public abstract class Task
   2: {
   3: }
   4:  
   5: public class BugTask : Task
   6: {
   7:     private string _resolution;
   8: }
   9:  
  10: public class FeatureTask : Task
  11: {
  12: }
  ```