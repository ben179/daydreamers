package com.daydreamers.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.daydreamers.dao.TagDAO;
import com.daydreamers.model.Tag;

@Repository
public class HibernateTagDAO extends HibernateGenericDAO<Tag, Long> implements TagDAO {

	@Override
	public Tag getOrCreateTag(String tag) {

		Tag found = getTagByValue(tag);
		
		if (found == null) {
			found = new Tag();
			found.setText(tag);
			getSession().saveOrUpdate(found);			
		}
		return found;
	}
	

	@Override
	public Tag getTagByValue(String tag) {	
		Criteria c = getSession().createCriteria(Tag.class);
		c.add(Restrictions.eq("text", tag));
		Tag found = (Tag) c.uniqueResult();
		return found;
	}


	@Override
	public void removeAllImages(Long id) {
		Tag tag = this.findById(id, true);
		tag.removeAllImages();
		getSession().flush();
	}
}
