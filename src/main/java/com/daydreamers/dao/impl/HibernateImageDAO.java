package com.daydreamers.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.daydreamers.dao.ImageDAO;
import com.daydreamers.model.Image;

@Repository
public class HibernateImageDAO extends HibernateGenericDAO<Image, Long> implements ImageDAO {

	@Override	
	public Image getImageForUser(String login, Long imageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> getImagesForUser(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
