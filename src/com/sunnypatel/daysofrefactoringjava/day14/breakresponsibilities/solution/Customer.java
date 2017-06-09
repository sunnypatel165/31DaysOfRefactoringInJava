package com.sunnypatel.daysofrefactoringjava.day14.breakresponsibilities.solution;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private List<Double> lateFees = new ArrayList<>();
	private List<Video> videos = new ArrayList<>();
	
	public void payFee(double fee){
		
	}
	/*
	 * Not by Sunny:
	 * I don't like anything about calculateBalance method below!
	 * 1. Why does it reside in Customer, shouldn't there be a separate class - like a controller/manager?
	 * 2. Let's say it can only live here, then why does it need to take customer as a param? Can it calculate for "this"?
	 */
	public double caclulateBalance(Customer customer){
		double sum = 0 ;
		List<Double> lateFees = customer.getLateFees();
		for(double lateFee : lateFees){
			sum += lateFee;
		}
		return sum;
	}
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
