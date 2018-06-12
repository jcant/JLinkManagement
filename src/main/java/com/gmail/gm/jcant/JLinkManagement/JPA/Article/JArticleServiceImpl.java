package com.gmail.gm.jcant.JLinkManagement.JPA.Article;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

public class JArticleServiceImpl implements JArticleService{

	@Autowired
    private JArticleRepositiory articleRepository;
	


	@Override
	public void addArticle(JArticle article) {
		articleRepository.save(article);
	}

	@Override
	public void updateArticle(JArticle article) {
		articleRepository.save(article);
	}

	@Override
	public List<JArticle> getByUser(JUser user) {
		List<JArticle> result = articleRepository.getByUser(user);
		return result;
	}
	
	@Override
	public List<JArticle> getInDateArticles(Date date) {
		List<JArticle> result = articleRepository.getInDateArticles(date);
		return result;
	}
	
}