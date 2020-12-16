package com.application.damz.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.application.damz.utils.Role;

/**
 * @author adam.mihoubi
 *
 */
@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private Long score ;
	
	@OneToMany(mappedBy="author", targetEntity = Article.class, cascade = CascadeType.ALL)
	private Set<Article> articles;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String email;
	
	@ElementCollection
	private List<Long> questions = new ArrayList<Long>();
	
	@NotNull
	private String password;
	
	@NotNull
	private Role role;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date inscriptionDate;
	
	@Temporal(TemporalType.DATE)
	private Date lastConnexionDate;
	
	
	private String fileName;
	
	private String fileType;
	
	@Lob
	private byte[] imageBlob;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getInscriptionDate() {
		return inscriptionDate;
	}
	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}
	public Date getLastConnexionDate() {
		return lastConnexionDate;
	}
	public void setLastConnexionDate(Date lastConnexionDate) {
		this.lastConnexionDate = lastConnexionDate;
	}
	public long getId() {
		return id;
	}
	public long getScore() {
		return this.score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Role getRole() {
		return this.role;
	}
	public List<Long> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Long> questions) {
		this.questions = questions;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getImageBlob() {
		return imageBlob;
	}
	public void setImageBlob(byte[] imageBlob) {
		this.imageBlob = imageBlob;
	}
	
	

}
