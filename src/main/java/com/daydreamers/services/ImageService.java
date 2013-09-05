package com.daydreamers.services;

import java.util.Set;
import java.util.List;

import com.daydreamers.model.Image;
import com.daydreamers.model.Tag;

public interface ImageService {

	Image makePersistent(Image image);
	Image readImageForUpdate(Long id);
	Image readImage(Long id);
	void saveOrUpdateImage(Image image);
	void deleteImage(Long id);

	List<Image> getAllImages();
	Set<Tag> getAllTagsForImage(Long id);
}
