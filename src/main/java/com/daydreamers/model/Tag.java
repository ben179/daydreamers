package com.daydreamers.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="TABLE_IMAGE_TAGS")
public class Tag implements Serializable {

	private static final long serialVersionUID = -3231366702008172022L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="TAG_TEXT", nullable=false, unique=true)
	private String text;
	
	@ManyToMany(mappedBy="tags")
	private Set<Image> taggedImages = new HashSet<Image>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<Image> getTaggedImages() {
		return Collections.unmodifiableSet(taggedImages);
	}
	
	public void addImage(Image image) {
		if (image != null) {
			taggedImages.add(image);
		}
	}
	
	public void removeImage(Image image) {
		if (taggedImages.contains(image)) {
			taggedImages.remove(image);
		}
	}
	
	public void removeAllImages() {
		for (Image image : taggedImages) {
			image.removeTagUnidirectional(this);
		}
		
		taggedImages.clear();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}	
	
}
