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
 1: public class RemoteControl
 2: {
 3: private string[] Functions { get; set; }
 4: private string Name { get; set; }
 5: private int CreatedYear { get; set; }
 6:
 7: public string PerformCoolFunction(string buttonPressed)
 8: {
 9: // Determine if we are controlling some extra function
 10: // that requires special conditions
 11: if (Functions.Length > 1 && Name == "RCA" &&
 CreatedYear > DateTime.Now.Year - 2)
 12: return "doSomething";
 13: }
 14: }
 
 1: public class RemoteControl
 2: {
 3: private string[] Functions { get; set; }
 4: private string Name { get; set; }
 5: private int CreatedYear { get; set; }
 6:
 7: private bool HasExtraFunctions
 8: {
 9: get { return Functions.Length > 1 && Name == "RCA" &&
 CreatedYear > DateTime.Now.Year - 2; }
 10: }
 11:
 12: public string PerformCoolFunction(string buttonPressed)
 13: {
 14: // Determine if we are controlling some extra function
 15: // that requires special conditions
 16: if (HasExtraFunctions)
 17: return "doSomething";
 18: }
 19: } 
