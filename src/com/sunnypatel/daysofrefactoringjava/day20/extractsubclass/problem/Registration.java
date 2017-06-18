package com.sunnypatel.daysofrefactoringjava.day20.extractsubclass.problem;

import java.util.Date;

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
