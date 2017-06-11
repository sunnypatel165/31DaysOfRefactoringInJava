package com.sunnypatel.daysofrefactoringjava.day16.encapsulateconditional.solution;

import java.time.LocalDateTime;

public class RemoteControl {
	private String[] functions;
	private String name;
	private int year;

	private boolean hasExtraFunctions = functions.length > 1 && name.compareTo("RCA") == 0
			&& year == LocalDateTime.now().getYear() - 1;

	public String performCoolFunction(String buttonPressed) {
		if (hasExtraFunctions)
			return "do something";
		return "Error!";
	}

	public String[] getFunctions() {
		return functions;
	}

	public void setFunctions(String[] functions) {
		this.functions = functions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
