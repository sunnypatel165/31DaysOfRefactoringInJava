package com.sunnypatel.daysofrefactoringjava.day14.breakresponsibilities.problem;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private List<Double> lateFees = new ArrayList<>();
	private List<Video> videos = new ArrayList<>();
	public List<Double> getLateFees() {
		return lateFees;
	}
	public void setLateFees(List<Double> lateFees) {
		this.lateFees = lateFees;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
}
