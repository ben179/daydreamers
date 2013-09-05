package com.daydreamers.dao;

import com.daydreamers.model.Tag;

public interface TagDAO extends GenericDAO<Tag, Long> {

	Tag getOrCreateTag(String tag);
	Tag getTagByValue(String tag);
	void removeAllImages(Long id);
}
