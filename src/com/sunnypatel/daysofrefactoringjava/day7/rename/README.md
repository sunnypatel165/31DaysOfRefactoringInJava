This refactoring I use most often and is one of the most useful refactoring. 
All too often we do not name methods/classes/parameters properly that leads to a misunderstanding as to what the method/class/parameter’s function is. When this occurs, assumptions are made and bugs are introduced to the system. 
As simple of a refactoring this seems, it is one of the most important to leverage.

```Java
public class Person
{
     private String FN;
     public String getFN() {
		return FN;
	}
	public void setFN(String fN) {
		this.FN = fN;
	}
	public double ClcHrlyPR() {
        // code to calculate hourly payrate
        return 0.0;
    }
}
```
As you can see here, we have a class/method/parameter that all have very non-descriptive, obscure names. They can be interpreted in a number of different ways. Applying this refactoring is as simple as renaming the items at hand to be more descriptive and convey what exactly they do. Simple enough.

```Java
public class Person
{
     private String firstName;
     public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public double calculateHourlyPayRate() {
        // code to calculate hourly payrate
        return 0.0;
    }
}
```


#### Original C# Code from the book
```cs
public class Person
{
    public string FN { get; set; }

    public decimal ClcHrlyPR()
    {
        // code to calculate hourly payrate
        return 0m;
    }
}
```

```cs
// Changed the class name to Employee
public class Employee
{
    public string FirstName { get; set; }

    public decimal CalculateHourlyPay()
    {
        // code to calculate hourly payrate
        return 0m;
    }
}
```
