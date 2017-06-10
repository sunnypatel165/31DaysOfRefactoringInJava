package com.sunnypatel.daysofrefactoringjava.day15.removeduplication.solution;

import java.time.LocalDateTime;

/*
 * We move the duplicated code to a shared method and voila! 
 * No more duplication. 
 * Please enforce this refactoring whenever possible. 
 * It leads to much fewer bugs because you aren’t copy/pasting the bugs throughout the code.
*/

public class MedicalRecord {
	private LocalDateTime dateArchived;

	private boolean isArchived;

	public void archiveRecord() {
		switchToArchive();
	}

	public void closeRecord() {
		switchToArchive();
	}

	public void switchToArchive() {
		this.dateArchived = LocalDateTime.now();
		this.isArchived = true;
	}

	public LocalDateTime getDateArchived() {
		return dateArchived;
	}

	public void setDateArchived(LocalDateTime dateArchived) {
		this.dateArchived = dateArchived;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

}
