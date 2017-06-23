Today’s refactoring is based on the c2 wiki entry and can be found [here](http://c2.com/cgi/wiki?ArrowAntiPattern). 
Los Techies own Chris Missal also did a very informative post on the antipattern that you can find [here](http://www.lostechies.com/blogs/chrismissal/archive/2009/05/27/anti-patterns-and-worst-practices-the-arrowhead-anti-pattern.aspx).

Simply put, the arrowhead antipattern is when you have nested conditionals so deep that they form an arrowhead of code.
I see this very often in different code bases and it makes for high [cyclomatic complexity](http://en.wikipedia.org/wiki/Cyclomatic_complexity) in code.
A good example of the arrowhead antipattern can be found in this sample here:

```Java
public class Security {
	private SecurityChecker checker;

	public Security(SecurityChecker checker) {
		this.checker = checker;
	}

	public boolean hasAccess(User user, Permission permission, List<Permission> excemptions) {
		boolean hasPermission = false;
		if (user != null) {
			if (permission != null) {
				if (excemptions.size() != 0) {
					if (checker.checkPermission(user, permission) || excemptions.contains(permission))
						hasPermission = true;
				}
			}
		}
		return hasPermission;
	}
}
```
Refactoring away from the arrowhead antipattern is as simple as swapping the conditionals to leave the method as soon as possible. 
Refactoring in this manner often starts to look like Design By Contract checks to evaluate conditions before performing the work of the method.
Here is what this same method might look like after refactoring.
```Java
public class Security {
	private SecurityChecker checker;

	public Security(SecurityChecker checker) {
		this.checker = checker;
	}

	public boolean hasAccess(User user, Permission permission, List<Permission> excemptions) {
		if (user == null || permission == null)
			return false;
		if (excemptions.contains(permission))
			return true;
		return checker.checkPermission(user, permission);
	}
}
```
As you can see, this method is much more readable and maintainable going forward. It’s not as difficult to
see all the different paths you can take through this method.

#### Original C# code from the book:
```cs
public class Security {
	public ISecurityChecker SecurityChecker { get; set; }
	
	public Security(ISecurityChecker securityChecker){
		SecurityChecker = securityChecker;
	}

	public bool HasAccess(User user, Permission permission, IEnumerable<Permission> exemptions) {
		bool hasPermission = false;
		if (user != null) {
			if (permission != null) {
				if (exemptions.Count() == 0) {
					if (SecurityChecker.CheckPermission(user, permission) || exemptions.Contains(permission)) {
						hasPermission = true;
					}
				}
			}
		}
		return hasPermission;
	}
}

//Solution:

public class Security {
	public ISecurityChecker SecurityChecker { get; set; }
	
	public Security(ISecurityChecker securityChecker) {
		SecurityChecker = securityChecker;
	}
	
	public bool HasAccess(User user, Permission permission, IEnumerable<Permission> exemptions) {
		if (user == null || permission == null)
			return false;
		if (exemptions.Contains(permission))
			return true;
		return SecurityChecker.CheckPermission(user, permission);
	}
}
```