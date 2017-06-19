package com.sunnypatel.daysofrefactoringjava.day21.collapsehierarchy.problem;

/*
 * Here we have a subclass that isn’t doing too much. It just has one property to denote if the site is active or not.
 * At this point maybe we realize that determining if a site is active is something we can use across the
 * board so we can collapse the hierarchy back into only a Website and eliminate the StudentWebsite type.
 */
public class StudentWebsite extends Website{
	private boolean isActive;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
