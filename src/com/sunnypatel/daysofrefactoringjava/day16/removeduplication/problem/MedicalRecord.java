package com.sunnypatel.daysofrefactoringjava.day16.removeduplication.problem;

import java.time.LocalDateTime;

public class MedicalRecord {
	private LocalDateTime dateArchived;
	
	private boolean isArchived;
	
	public void archiveRecord(){
		this.dateArchived = LocalDateTime.now();
		this.isArchived = true;
	}
	public void closeRecord(){
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
