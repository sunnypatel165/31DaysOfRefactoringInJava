package com.sunnypatel.daysofrefactoringjava.day21.collapsehierarchy.problem;

import java.util.List;

public class Website {
	private String title;
	private String description;
	//This should NOT be a list of "Objects" but that's not the main point.
	private List<Object> pages;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Object> getPages() {
		return pages;
	}
	public void setPages(List<Object> pages) {
		this.pages = pages;
	}
}