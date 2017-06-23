package com.sunnypatel.daysofrefactoringjava.day24.removearrowheadantipattern.solution;

import java.util.List;

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
