Todays refactoring comes from Martin Fowlers catalog of patterns. You can find this refactoring in his catalog [here](http://refactoring.com/catalog/extractSubclass.html).

This refactoring is useful when you have methods on a base class that are not shared amongst all classes and needs to be pushed down into it’s own class. 
The example I’m using here is pretty straightforward. 
We start out with a single class called Registration. 
This class handles all information related to a student registering for a course.

```Java
public class NonRegistrationAction {
	public String someActionThatCanBeDoneWithoutRegistration() {
		return null;
	}
}
public class Registration {
	private NonRegistrationAction action;
	private  int RegistrationTotal;
	private String notes;
	private String description;
	private Date RegistrationDate;
	public NonRegistrationAction getAction() {
		return action;
	}
	public void setAction(NonRegistrationAction action) {
		this.action = action;
	}
	public int getRegistrationTotal() {
		return RegistrationTotal;
	}
	public void setRegistrationTotal(int registrationTotal) {
		RegistrationTotal = registrationTotal;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRegistrationDate() {
		return RegistrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		RegistrationDate = registrationDate;
	}
}
```
There is something that we’ve realized after working with this class. We are using it in two different contexts. 
The properties NonRegistrationAction and Notes are only ever used when dealing with a NonRegistration which is used to track a portion of the system that is slightly different than a normal registration.
Noticing this, we can extract a subclass and move those properties down into the NonRegistration class where they more appropriately fit.

```Java
public class NonRegistrationAction {
	public String someActionThatCanBeDoneWithoutRegistration() {
		return null;
	}
}
public class Registration {
	private int RegistrationTotal;
	private String description;
	private Date RegistrationDate;

	public int getRegistrationTotal() {
		return RegistrationTotal;
	}

	public void setRegistrationTotal(int registrationTotal) {
		RegistrationTotal = registrationTotal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegistrationDate() {
		return RegistrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		RegistrationDate = registrationDate;
	}
}

public class NonRegistration extends Registration {

	private NonRegistrationAction action;
	private String notes;

	public NonRegistrationAction getAction() {
		return action;
	}

	public void setAction(NonRegistrationAction action) {
		this.action = action;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
```
 
#### Original C# code from the book
```cs
public class Registration {
	public NonRegistrationAction Action { get; set; }
	public decimal RegistrationTotal { get; set; }
	public string Notes { get; set; }
	public string Description { get; set; }
	public DateTime RegistrationDate { get; set; }
}

//Solution:
public class Registration
{
	public decimal RegistrationTotal { get; set; }
	public string Description { get; set; }
	public DateTime RegistrationDate { get; set; }
}
public class NonRegistration : Registration {
	public NonRegistrationAction Action { get; set; }
	public string Notes { get; set; }
}
```
