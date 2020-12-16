package com.application.damz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.damz.entities.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {
	
}	
