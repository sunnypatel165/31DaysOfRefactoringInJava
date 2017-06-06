package com.sunnypatel.daysofrefactoringjava.day11.switchtostrategy.solution;

import java.util.HashMap;
import java.util.Map;

public class ShippingInfo {
	private Map<State, ShippingCalculator> shippingCalculations;
	
	public ShippingInfo(){
		shippingCalculations = new HashMap<>();
		shippingCalculations.put(State.Alaska, new AlaskaShippingCalculator());
		shippingCalculations.put(State.NewYork, new NewYorkShippingCalculator());
		shippingCalculations.put(State.Florida, new FloridaShippingCalculator());
	}
	
	public int calculateShippingCost(State state){
		return shippingCalculations.get(state).calculate();
	}

	public Map<State, ShippingCalculator> getShippingCalculations() {
		return shippingCalculations;
	}

	public void setShippingCalculations(Map<State, ShippingCalculator> shippingCalculations) {
		this.shippingCalculations = shippingCalculations;
	}
}
