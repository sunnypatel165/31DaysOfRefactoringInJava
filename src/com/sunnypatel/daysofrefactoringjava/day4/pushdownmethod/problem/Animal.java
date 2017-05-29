package com.sunnypatel.daysofrefactoringjava.day4.pushdownmethod.problem;

/*
So here we have some code with a base class that has a Bark method. 
Perhaps at one time our cat could bark, but now we no longer need that functionality on the Cat class. 
So we “Push Down” the Bark method into the Dog class as it is no longer needed on the base class but perhaps it is still needed when dealing explicitly with a Dog. 
At this time, it’s worthwhile to evaluate if there is any behavior still located on the Animal base class. 
If not, it is a good opportunity to turn the Animal abstract class into an interface instead as no code is required on the contract and can be treated as an interface.
 */
public class Animal {
	public void bark(){
		System.out.println("Bark!");
	}
	public void walk(){
		System.out.println("WALKING!");
	}

}
