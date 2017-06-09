package com.sunnypatel.daysofrefactoringjava.day14.breakresponsibilities.problem;

import java.util.List;

public class Video {
	public void payFee(double fee){
		
	}
	public void rentVideo(Video video, Customer customer){
		List<Video> videos = customer.getVideos();
		videos.add(video);
		customer.setVideos(videos);
	}
	public double calculateBalance(Customer customer){
		double sum = 0 ;
		List<Double> lateFees = customer.getLateFees();
		for(double lateFee : lateFees){
			sum += lateFee;
		}
		return sum;
	}
}
