package com.sunnypatel.daysofrefactoringjava.day26.removedoublenegative.solution;

import java.util.List;

public class Order {
	public void checkout(List<Product> products, Customer customer){
		if(customer.isFlagged()){
			//the customer is flagged, can't process
			return;
		}
		//do normal order checkout
	}

}
