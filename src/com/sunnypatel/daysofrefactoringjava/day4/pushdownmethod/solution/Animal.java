package com.sunnypatel.daysofrefactoringjava.day4.pushdownmethod.solution;

/*
 * We just "Pushed down" the bark method to Dog class as it was not needed at the abstract level
 * At the same time Animal can be made into an abstract class or interface as it doesn't have anything except methods for now.
 * On the other hand, if one of the child classes that could "Fly", you might want to push down the walk method too!
 * */
public abstract class Animal {
	public void walk(){
		System.out.println("WALKING!");
	}
	//No bark method needed here!

}
