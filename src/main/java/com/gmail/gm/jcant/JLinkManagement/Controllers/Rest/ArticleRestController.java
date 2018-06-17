package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomain;
import com.gmail.gm.jcant.JLinkManagement.JPA.Article.JArticle;
import com.gmail.gm.jcant.JLinkManagement.JPA.Article.JArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ArticleRestController {

    @Autowired
    private JArticleService articleService;

    @RequestMapping(value = "/articles/getActual")//, method = RequestMethod.POST)
    @JDomain(property = "frontend.domains")
    public List<JArticle> getArticles(){
        //System.out.println("sending articles...");
        return articleService.getInDateArticles(new Date());
    }
}
