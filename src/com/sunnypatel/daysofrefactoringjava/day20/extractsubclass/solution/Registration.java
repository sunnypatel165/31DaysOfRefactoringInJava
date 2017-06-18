package com.sunnypatel.daysofrefactoringjava.day20.extractsubclass.solution;

import java.util.Date;

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
