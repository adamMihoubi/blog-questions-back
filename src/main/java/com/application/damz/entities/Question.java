package com.application.damz.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.application.damz.utils.Cathegory;
import com.application.damz.utils.Level;

@Entity
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ElementCollection
	private List<String> responses = new ArrayList<String>();
	
	@NotNull
	private String question;
	
	@NotNull
	private Level level;

	@NotNull
	private Cathegory cathegory;
	
	@NotNull
	private String answer;
	
	
	
	public List<String> getResponses() {
		return responses;
	}

	public void setResponses(List<String> responses) {
		this.responses = responses;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Cathegory getCathegory() {
		return this.cathegory;
	}
	
	public void setCathegory(Cathegory cathegory) {
		this.cathegory = cathegory;
	}
	
	public long getId() {
		return id;
	}
	
	public String getAnswer() {
		return this.answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
}
