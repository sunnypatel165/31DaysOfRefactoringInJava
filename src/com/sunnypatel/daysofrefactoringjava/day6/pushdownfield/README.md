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
```cs
public abstract class Task
{
    protected string _resolution;
}

public class BugTask : Task
{
}

public class FeatureTask : Task
{
}
```

```cs
public abstract class Task
{
}

public class BugTask : Task
{
    private string _resolution;
}

public class FeatureTask : Task
{
}
```
