package com.daydreamers.dao;

import java.util.List;

import com.daydreamers.model.Image;

public interface ImageDAO extends GenericDAO<Image, Long> {
	
	Image getImageForUser(String login, Long imageId);
	List<Image> getImagesForUser(String login);
	
}
