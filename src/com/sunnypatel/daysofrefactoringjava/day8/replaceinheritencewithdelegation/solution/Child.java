package com.sunnypatel.daysofrefactoringjava.day8.replaceinheritencewithdelegation.solution;

/*
 * In this instance, a Child is not a “Sanitation” and therefore doesn’t make sense as an inheritance hierarchy. 
 * We can refactor by initializing an instance of Sanitation in the Child constructor and delegating the call to the class rather than via inheritance. 
 * If you were using Dependency Injection, you would pass in the Sanitation instance via the constructor, although then you would need to register your model in your IoC container which is a smell IMO, you get the idea though. 
 * Inheritance should ONLY be used for scenarios where inheritance is warranted. 
 * Not instances where it makes it quicker to throw down code.
 */
public class Child {
	private Sanitation sanitation;

	public Sanitation getSanitation() {
		return sanitation;
	}

	public void setSanitation(Sanitation sanitation) {
		this.sanitation = sanitation;
	}
	public String washHands(){
		return sanitation.washHands();
	}
	

}
