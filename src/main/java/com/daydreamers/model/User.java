package com.daydreamers.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TABLE_USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Version
	@Column(name="VERSION")
	private Integer version;
	
	@Column(name="FIRST_NAME", nullable=true)
	@Size(max=20, message="{user.firstname.length}")	
	private String firstName;
	
	@Column(name="LAST_NAME", nullable=false)
	@NotBlank(message="{user.lastname.notnull}")
	@Size(max=20, message="{user.lastname.length}")
	private String lastName;
	
	@Column(name="LOGIN", nullable=false, unique=true)
	@Pattern(regexp = "[a-z]{3}\\d{3}", message="{user.login.pattern}")
	private String login;
	
	@Column(name="PASSWORD", nullable=false)
	@Size(min=5, message="{user.password.length}")
	private String password;
	
	@Column(name="EMAIL", nullable=false, unique=true)
	@NotBlank(message="{user.email.notnull}")
	@Email(message="{user.email.notvalid}")
	private String email;
	
	@Column(name="USER_FACE")
	@Lob
	private byte[] face;
	
	@OneToMany(mappedBy="user", cascade={CascadeType.ALL})
	private Set<Image> images = new HashSet<Image>();
	
	@OneToOne
	@JoinColumn(name="PROFILE_IMAGE_ID")
	private Image profileImage;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public byte[] getFace() {
		return face;
	}
	public void setFace(byte[] face) {
		this.face = face;
	}
	public Set<Image> getImages() {
		return Collections.unmodifiableSet(images);
	}
	
	public void addImage(Image image) {
		images.add(image);
		image.setUser(this);
	}
	
	public void removeImage(Image image) {
		if (images.contains(image)) {
			images.remove(image);
			image.setUser(null);
		}
	}
	
	public Image getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Image profileImage) {
		this.profileImage = profileImage;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "User: " + login + " Version : " + version + " Id: " + id;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
