package com.sunnypatel.daysofrefactoringjava.day12.breakdependencies.solution;

public class AnimalFeedingService {
	private FeederService feederService;
	public boolean isBowlEmpty;

	public AnimalFeedingService(FeederService feederService) {
		this.feederService = feederService;
	}

	public void feed() {
		if (isBowlEmpty) {
			feederService.replenishFood();
		}
	}

	public FeederService getFeederService() {
		return feederService;
	}

	public void setFeederService(FeederService feederService) {
		this.feederService = feederService;
	}

	public boolean isBowlEmpty() {
		return isBowlEmpty;
	}

	public void setBowlEmpty(boolean isBowlEmpty) {
		this.isBowlEmpty = isBowlEmpty;
	}

}
