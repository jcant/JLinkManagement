package com.gmail.gm.jcant.JLinkManagement.JPA.Article;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import org.springframework.stereotype.Service;

@Service
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
		
		return articleRepository.getByUser(user);
	}
	
	@Override
	public List<JArticle> getInDateArticles(Date date) {
		
		return articleRepository.getInDateArticles(date);
	}

	@Override
	public JArticle getById(long id) throws JArticleException {
		
		JArticle article = articleRepository.getOne(id);
		if (article == null) {
			throw new JArticleException("NO JArticle found for id="+id);
		}
		return articleRepository.getOne(id);
	}

	@Override
	public void save(JArticle article) {
		articleRepository.save(article);
	}
	
	
}