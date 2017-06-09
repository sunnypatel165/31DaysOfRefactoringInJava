package com.sunnypatel.daysofrefactoringjava.day14.breakresponsibilities.solution;

import java.util.List;

/*
 * As you can see here, the Video class has two responsibilities, once for handling video rentals, and another
for managing how many rentals a customer has. 
We can break out the customer logic into it’s own class to help seperate the responsibilities.
 */
public class Video {
	public void rentVideo(Video video, Customer customer){
		List<Video> videos = customer.getVideos();
		videos.add(video);
		customer.setVideos(videos);
	}

}
