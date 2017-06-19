Todays refactoring comes from Martin Fowlers catalog of patterns. You can find this refactoring in his catalog [here](http://refactoring.com/catalog/collapseHierarchy.html).

Yesterday we looked at extracting a subclass for moving responsibilities down if they are not needed across the board. 
A Collapse Hierarchy refactoring would be applied when you realize you no longer need a subclass.
When this happens it doesn’t really make sense to keep your subclass around if it’s properties can be merged into the base class and used strictly from there.

```Java
public class Website {
	private String title;
	private String description;
	//This should NOT be a list of "Objects" but that's not the main point.
	private List<Object> pages;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Object> getPages() {
		return pages;
	}
	public void setPages(List<Object> pages) {
		this.pages = pages;
	}
}
public class StudentWebsite extends Website{
	private boolean isActive;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
```
 
Here we have a subclass that isn’t doing too much. It just has one property to denote if the site is active or not.
At this point maybe we realize that determining if a site is active is something we can use across the
board so we can collapse the hierarchy back into only a Website and eliminate the StudentWebsite type.

```Java
public class Website {
	private String title;
	private String description;
	// This should NOT be a list of "Objects" but that's not the main point.
	private List<Object> pages;
	private boolean isActive;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Object> getPages() {
		return pages;
	}

	public void setPages(List<Object> pages) {
		this.pages = pages;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
```
 
 
#### Original C# code from the book:
```cs
public class Website {
	public string Title { get; set; }
	public string Description { get; set; }
	public IEnumerable<Webpage> Pages { get; set; }
}
public class StudentWebsite : Website{
	public bool IsActive { get; set; }
}

//Solution: 
public class Website {
	public string Title { get; set; }
	public string Description { get; set; }
	public IEnumerable<Webpage> Pages { get; set; }
	public bool IsActive { get; set; }
}
``` 
