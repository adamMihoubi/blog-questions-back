package com.application.damz.cotrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.damz.dao.ArticleRepository;
import com.application.damz.entities.Article;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping(value="/articles",method=RequestMethod.GET)
	public List<Article> getArticles(){
		return articleRepository.findAll();
	}
	
	@RequestMapping(value="/addArticle",method=RequestMethod.POST)
	public Article save(@RequestBody Article article) {
		System.out.println(article);
		return articleRepository.save(article);
	}
}
