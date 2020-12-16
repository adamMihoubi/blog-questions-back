package com.application.damz.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.damz.entities.Question;
import com.application.damz.utils.Level;

public interface QuestionRepository extends JpaRepository<Question,Long>{
	public Set<Question> findQuestionsByLevel(Level level);
}
