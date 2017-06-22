This refactoring comes from Fowler’s refactoring catalog and can be found [here](http://refactoring.com/catalog/introduceParameterObject.html)

Sometimes when working with a method that needs several parameters it becomes difficult to read the method signature because of five or more parameters being passed to the method like so:

```Java
public class Registration {
	public void create(double amount, Student student, List<Course> courses, double credits) {
		// do work
	}
}
```

In this instances it’s useful to create a class who’s only responsibility is to carry parameters into the method. 
This helps make the code more flexible because to add more parameters, you need only to add another field to the parameter object.

```Java
public class RegistrationContext {
	private double amount;
	private Student student;
	private List<Course> courses;
	private double credits;

	public RegistrationContext(double amount, Student student, List<Course> courses, double credits) {
		this.amount = amount;
		this.student = student;
		this.courses = courses;
		this.credits = credits;
	}

	public double getAmount() {
		return amount;
	}

	public Student getStudent() {
		return student;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public double getCredits() {
		return credits;
	}
}
public class Registration {
	public void create(RegistrationContext context) {
		// do work
	}
}
```

Be careful to only use this refactoring when you find that you have a large number of parameters to pass to the method however as it does add several more classes to your codebase and should be kept to a minimum.

#### Original C# code form the book:
```cs
public class Registration {
	public void Create(decimal amount, Student student, IEnumerable<Course> courses, decimal credits) {
		// do work
	}
}
public class RegistrationContext {
	public decimal Amount { get; set; }
	public Student Student { get; set; }
	public IEnumerable<Course> Courses { get; set; }
	public decimal Credits { get; set; }
}

public class Registration {
	public void Create(RegistrationContext registrationContext) {
		// do work
	}
}
```