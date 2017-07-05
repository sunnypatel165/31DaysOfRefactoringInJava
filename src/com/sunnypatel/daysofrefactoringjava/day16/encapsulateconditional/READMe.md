Sometimes when doing a number of different checks within a conditional the intent of what you are testing
for gets lost in the conditional. In these instances I like to extract the conditional into an easy to read
property, or method depending if there is parameters to pass or not. Here is an example of what the code
might look like before:

```Java
public class RemoteControl {
	private String[] functions;
	private String name;
	private int year;

	public String performCoolFunction(String buttonPressed) {
		if (functions.length > 1 && name.compareTo("RCA") == 0 && year == LocalDateTime.now().getYear() - 1)
			return "do something";
		return "Error!";
	}

	public String[] getFunctions() {
		return functions;
	}

	public void setFunctions(String[] functions) {
		this.functions = functions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
```

 
After we apply the refactoring, you can see the code reads much easier and conveys intent:

```Java
public class RemoteControl {
	private String[] functions;
	private String name;
	private int year;

	private boolean hasExtraFunctions = functions.length > 1 && 
					    name.compareTo("RCA") == 0 &&
					    year == LocalDateTime.now().getYear() - 1;

	public String performCoolFunction(String buttonPressed) {
		if (hasExtraFunctions)
			return "do something";
		return "Error!";
	}

	public String[] getFunctions() {
		return functions;
	}

	public void setFunctions(String[] functions) {
		this.functions = functions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
```
 
 #### Original C# code from the book:
```cs
public class RemoteControl {
	private string[] Functions { get; set; }
	private string Name { get; set; }
	private int CreatedYear { get; set; }
	public string PerformCoolFunction(string buttonPressed) {
		// Determine if we are controlling some extra function
		// that requires special conditions
		if (Functions.Length > 1 && Name == "RCA" && CreatedYear > DateTime.Now.Year - 2)
			return "doSomething";
	}
}
 
public class RemoteControl {
	private string[] Functions { get; set; }
	private string Name { get; set; }
	private int CreatedYear { get; set; }
	private bool HasExtraFunctions {
		get { return Functions.Length > 1 && Name == "RCA" && CreatedYear > DateTime.Now.Year - 2; }
	}
	public string PerformCoolFunction(string buttonPressed) {
		// Determine if we are controlling some extra function
		// that requires special conditions
		if (HasExtraFunctions)
			return "doSomething";
	}
}``` 
