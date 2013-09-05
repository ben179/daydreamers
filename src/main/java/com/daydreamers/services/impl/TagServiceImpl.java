package com.daydreamers.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daydreamers.dao.ImageDAO;
import com.daydreamers.dao.TagDAO;
import com.daydreamers.model.Image;
import com.daydreamers.model.Tag;
import com.daydreamers.services.TagService;

@Service
@Transactional(readOnly=false)
public class TagServiceImpl implements TagService {

	@Inject
	private TagDAO tagDao;
	
	@Inject
	private ImageDAO imageDao;
	
	@Transactional(readOnly=true)
	@Override
	@SuppressWarnings("unchecked")
	public Set<Image> getAllImagesForTag(String tag) {
	
		Tag found = getTagByValue(tag);
		
		if (found != null) {
			return Collections.unmodifiableSet(found.getTaggedImages());
		}
		
		return Collections.EMPTY_SET;
	}

	@Override
	public void addTagForImage(Long imageId, String tag) {
		Image image = imageDao.findById(imageId, true);
		
		if (image != null) {
			Tag t = tagDao.getOrCreateTag(tag);
			image.addTag(t);
		}
	}

	@Override
	public void removeTagForImage(Long imageId, String tag) {
		Image image = imageDao.findById(imageId, true);
		if (image != null) {
			Tag t = tagDao.getTagByValue(tag);
			image.removeTag(t);
		}		
	}

	@Transactional(readOnly=true)
	@Override
	public Tag getTagByValue(String tag) {
		Tag t = tagDao.getTagByValue(tag);	
		return t;
	}

	@Override
	public void addTagForImage(Long imageId, Tag tag) {		
		addTagForImage(imageId, tag.getText());	
	}

	@Override
	public List<Tag> getAllTags() {
		return tagDao.findAll();
	}

	@Override
	public void removeTag(Long tagId) {
		Tag tag = tagDao.findById(tagId, true);
		if (tag != null) {
			tagDao.removeAllImages(tagId);
			tagDao.remove(tagId);
		}
	}

	@Override
	public void removeAllImages(Long tagId) {
		tagDao.removeAllImages(tagId);
	}
}
