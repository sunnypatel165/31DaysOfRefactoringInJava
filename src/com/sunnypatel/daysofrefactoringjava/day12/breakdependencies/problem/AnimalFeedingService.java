package com.sunnypatel.daysofrefactoringjava.day12.breakdependencies.problem;

public class AnimalFeedingService {
	private boolean isBowlEmpty;
	
	public void feed(){
		if(isBowlEmpty){
			Feeder.replenishFood();
		}
	}
	
	public boolean isBowlEmpty() {
		return isBowlEmpty;
	}
	public void setBowlEmpty(boolean isBowlEmpty) {
		this.isBowlEmpty = isBowlEmpty;
	}
}
