package com.sunnypatel.daysofrefactoringjava.day20.extractsubclass.solution;

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
