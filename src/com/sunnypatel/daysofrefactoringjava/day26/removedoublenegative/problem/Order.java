package com.sunnypatel.daysofrefactoringjava.day26.removedoublenegative.problem;

import java.util.List;

public class Order {
	public void checkout(List<Product> products, Customer customer){
		if(!customer.isNotFlagged()){
			//the customer is flagged, can't process
			return;
		}
		//do normal order checkout
	}

}
