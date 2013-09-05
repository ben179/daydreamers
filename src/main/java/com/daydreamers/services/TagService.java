package com.daydreamers.services;

import java.util.List;
import java.util.Set;

import com.daydreamers.model.Image;
import com.daydreamers.model.Tag;

public interface TagService {

	Set<Image> getAllImagesForTag(String tag);
	void addTagForImage(Long imageId, String tag);
	void addTagForImage(Long imageId, Tag tag);
	void removeTagForImage(Long imageId, String tag);
	Tag getTagByValue(String tag);
	
	void removeTag(Long tagId);
	void removeAllImages(Long tagId);
	List<Tag> getAllTags();
}
