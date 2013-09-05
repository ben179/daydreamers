package com.daydreamers.services.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daydreamers.dao.ImageDAO;
import com.daydreamers.model.Image;
import com.daydreamers.model.Tag;
import com.daydreamers.services.ImageService;

@Service
@Transactional(readOnly=true)
public class ImageServiceImpl implements ImageService {

	@Inject
	private ImageDAO dao;
	
	@Override
	@Transactional(readOnly=false)
	public void saveOrUpdateImage(Image image) {
		dao.saveOrUpdate(image);
	}

	@Override
	public Image readImageForUpdate(Long id) {
		return dao.findById(id, true);
	}

	@Override
	public Image readImage(Long id) {
		Image image = dao.findById(id, false);
		return image;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Image makePersistent(Image image) {
		return dao.makePersistent(image);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteImage(Long id) {
		dao.remove(id);
	}

	@Override
	public List<Image> getAllImages() {
		List<Image> images = dao.findAll();
		return images;
	}

	@Override
	public Set<Tag> getAllTagsForImage(Long id) {
		Image image = readImage(id);
		Set<Tag> tags = image.getTags();
		return tags;
	}
}
