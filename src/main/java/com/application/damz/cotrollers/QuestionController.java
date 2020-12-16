package com.application.damz.cotrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.damz.dao.QuestionRepository;
import com.application.damz.entities.Question;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;
	
	@RequestMapping(value="/questions",method=RequestMethod.GET)
	public List<Question> getQuestions(){
		return questionRepository.findAll();
	}
	
	@RequestMapping(value="/addQuestion",method=RequestMethod.POST)
	public Question save(@RequestBody Question question) {
		return questionRepository.save(question);
	}
	
}
