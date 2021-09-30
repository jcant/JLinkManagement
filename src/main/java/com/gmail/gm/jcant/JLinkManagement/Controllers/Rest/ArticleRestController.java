package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import com.gmail.develop.jcant.JDate;
import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.JOperationInfo;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertising;
import com.gmail.gm.jcant.JLinkManagement.JPA.Article.JArticle;
import com.gmail.gm.jcant.JLinkManagement.JPA.Article.JArticleException;
import com.gmail.gm.jcant.JLinkManagement.JPA.Article.JArticleService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ArticleRestController {

	@Autowired
	private Logger logger;
	
    @Autowired
    private JArticleService articleService;
    @Autowired
    private JUserService userService;

    @RequestMapping(value = "/articles/getActual")
    @JDomain(property = "frontend.domains")
    public List<JArticle> getArticles(){
        return articleService.getInDateArticles(new Date());
    }
    
    @RequestMapping(value = "/articles/{id}")
    @JDomain(property = "frontend.domains")
    public JArticle getArticleById(@PathVariable(value = "id") long id) throws JArticleException{

        return articleService.getById(id);
    }
    
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.POST)
    @JDomain(property = "frontend.domains")
    public JOperationInfo<JArticle> saveArticle(@PathVariable(value = "id") long id,
    							@RequestParam String header,
    							@RequestParam(required = false) String text,
    							@RequestParam(required = false) String created,
    							@RequestParam(required = false) String pubStart,
    							@RequestParam(required = false) String pubFinish,
    							@RequestParam String login) throws JArticleException, JUserException {

    	logger.warn("Sorry! It's a test server, Article operations are disabled!");
    	
    	return new JOperationInfo<JArticle>("Sorry! It's a test server, Article operations are disabled!", false);
    	
    	
    	
//    	JDate.setDefaultDateFormat("yyyy-MM-dd");
//    	Date dCreated = null;
//
//    	if (created == null) {
//    		dCreated = new Date();
//    	} else {
//    		dCreated = JDate.getDate(created);
//    	}
//    	
//    	JUser author = userService.getUserByLogin(login);
//    	JArticle article = null;
//
//    	if (id != -1) {
//    		article = articleService.getById(id);
//    	} else {
//    		article = new JArticle();
//    	}
//    	
//    	article.setHeader(header);
//    	if (text != null) article.setText(text);
//    	article.setCreated(dCreated);
//    	if (pubStart != null) article.setPubStart(JDate.getDate(pubStart));
//    	if (pubFinish != null) article.setPubFinish(JDate.getDate(pubFinish));
//    	article.setAuthor(author);
//
//    	JDate.setDefaultDateFormat("dd-MM-yyyy");
//    	
//    	articleService.save(article);
//    	
//        return new JOperationInfo<JArticle>("Article update(create) success!", true);
    }

	@RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
	@JDomain(property = "frontend.domains")
	public JOperationInfo<JArticle> deleteArticle(@PathVariable(value = "id") long id) throws JArticleException {
		
		logger.warn("Sorry! It's a test server, Article operations are disabled!");
    	
    	return new JOperationInfo<JArticle>("Sorry! It's a test server, Article operations are disabled!", false);
		
//		articleService.deleteById(id);
//		return new JOperationInfo<JArticle>("Article delete success!", true);
	}
}
