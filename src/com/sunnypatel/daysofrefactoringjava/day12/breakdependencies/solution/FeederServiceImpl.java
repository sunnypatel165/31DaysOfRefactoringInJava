package com.sunnypatel.daysofrefactoringjava.day12.breakdependencies.solution;

import com.sunnypatel.daysofrefactoringjava.day12.breakdependencies.solution.Feeder;

public class FeederServiceImpl implements FeederService{

	@Override
	public void replenishFood() {
		Feeder.replenishFood();	
	}

}
