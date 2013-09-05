package com.daydreamers.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TABLE_IMAGES")
public class Image implements Serializable {

	private static final int THUMBNAIL_WIDTH = 320;
	private static final long serialVersionUID = -5140203974579498225L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Lob
	@Column(name="IMAGE_CONTENT", nullable=false)
	private byte[] content;
	
	@Lob
	@Column(name="IMAGE_THUMBNAIL", nullable=false)
	private byte[] thumbnail;
	
	@NotBlank(message="{image.name.notnull}")
	@Column(name="IMAGE_NAME", nullable=false)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="IMAGE_ADDED", nullable=false)
	private Date dateAdded;
		
	@ManyToMany
	@JoinTable(name="IMAGE_TAG", joinColumns={ @JoinColumn(name="IMAGE_ID")}, inverseJoinColumns={@JoinColumn(name="TAG_ID")})
	private Set<Tag> tags = new HashSet<Tag>();
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="USER_ID", nullable=true)
	private User user;

	@Column(name="WIDTH")
	private int width;
	
	@Column(name="HEIGHT")
	private int height;
	
	@Column(name="THUMBNAIL_WIDTH")
	private int thumbWidth;
	
	@Column(name="THUMBNAIL_HEIGHT")
	private int thumbHeight;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		
		this.content = content;

		try {
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(getContent()));
			
			this.width = image.getWidth();
			this.height = image.getHeight();
			this.thumbWidth = THUMBNAIL_WIDTH;
			this.thumbHeight = (int)((thumbWidth / (float) width) * height);
			
			java.awt.Image img = image.getScaledInstance(thumbWidth, thumbHeight, BufferedImage.SCALE_SMOOTH);	
			BufferedImage thumb = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
			thumb.getGraphics().drawImage(img, 0, 0, null );
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(thumb, "jpg", baos);
			
			setThumbnail(baos.toByteArray());
		
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
		
	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Set<Tag> getTags() {
		return Collections.unmodifiableSet(tags);
	}

	public void addTag(Tag tag) {
		if (tag != null) {
			tags.add(tag);
			tag.addImage(this);
		}
	}
	
	public void removeTag(Tag tag) {
		if (tag != null && tags.contains(tag)) {
			tags.remove(tag);
			tag.removeImage(this);
		}
	}

	public void removeTagUnidirectional(Tag tag) {
		if (tag != null && tags.contains(tag)) {
			tags.remove(tag);
		}		
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(thumbnail);
		result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Image other = (Image) obj;
		
		if (dateAdded == null) {
			if (other.dateAdded != null)
				return false;
		} else if (!dateAdded.equals(other.dateAdded))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (!Arrays.equals(thumbnail, other.thumbnail))
			return false;
		
		return true;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getThumbWidth() {
		return thumbWidth;
	}

	public int getThumbHeight() {
		return thumbHeight;
	}
	
	
}
