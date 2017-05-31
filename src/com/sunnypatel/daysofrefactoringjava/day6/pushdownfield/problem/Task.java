package com.sunnypatel.daysofrefactoringjava.day6.pushdownfield.problem;

/*
 * 
In this example, we have a string field that is only used by one derived class, and thus can be pushed down as no other classes are using it. 
It’s important to do this refactoring at the moment the base field is no longer used by other derived classes. 
The longer it sits the more prone it is for someone to simply not touch the field and leave it be.
 */
public class Task {
	String resolution;

}
