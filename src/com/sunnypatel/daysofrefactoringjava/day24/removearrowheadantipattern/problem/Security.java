package com.sunnypatel.daysofrefactoringjava.day24.removearrowheadantipattern.problem;

import java.util.List;

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
