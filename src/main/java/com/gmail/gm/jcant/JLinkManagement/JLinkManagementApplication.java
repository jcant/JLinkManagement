package com.gmail.gm.jcant.JLinkManagement;

import com.gmail.develop.jcant.JDate;
import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomainRequestMappingHandlerMapping;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertising;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertisingService;
import com.gmail.gm.jcant.JLinkManagement.JPA.Article.JArticle;
import com.gmail.gm.jcant.JLinkManagement.JPA.Article.JArticleService;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserDetailServiceImpl;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserRole;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpringBootApplication
@EnableWebMvc
public class JLinkManagementApplication implements WebMvcConfigurer{
	
	public static void main(String[] args) {
		SpringApplication.run(JLinkManagementApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(
		final JUserService userService,
		final JLinkService linkService,
		final JRootLinkService rlinkService,
		final JArticleService articleService,
		final JAdvertisingService advService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				//initDB(userService, linkService, rlinkService, articleService, advService);
			}
		};
	}


	private void initDB(final JUserService userService,
			final JLinkService linkService,
			final JRootLinkService rlinkService,
			final JArticleService articleService,
			final JAdvertisingService advService) {
		JUser admin = new JUser("admin", "$2a$10$mvuMNa9iOkNJK1LyWLPj9uh.xaICWGjC78iRZkkdF9auHDjZLbjx.", JUserRole.ADMIN);
		JUser ouser = new JUser("user", "$2a$10$mvuMNa9iOkNJK1LyWLPj9uh.xaICWGjC78iRZkkdF9auHDjZLbjx.", JUserRole.USER);

		userService.addUser(admin);
		userService.addUser(ouser);

		JArticle art1 = new JArticle(new Date(), null, null, "Weclome!",
			"It's good to see you here!<br>"+
			"This is a test project of the redirect platform. You can try the user and admin functionality, as well as the basic <strong>redirect functionality.</strong><br>"+
			"</br>"+
			"Registration of a new user is disabled<br>" +
			"Creation of a new links is disabled<br>" +
			"You can log in either as administrator or as ordinaly user",
			admin);

		JArticle art2 = new JArticle(new Date(), null, null, "Admin",
			"to Auth as an ADMIN, please input:<br>"+
			"<br>"+
			"login: <strong>admin</strong><br>"+
			"password: <strong>password</strong>",
			admin);

		 JArticle art3 = new JArticle(new Date(), null, null, "User",
		 	"to Auth as an USER, please input:<br>"+
		 	"<br>"+
		 	"login: <strong>user</strong><br>"+
		 	"password: <strong>password</strong>",
		 	admin);

		//JArticle art4 = new JArticle(new Date(), null, null, "Redirecting",
		//	"Due to \"heroku\" limitations You may create links based on only one root link: <strong>short-domain-name.herokuapp.com</strong><br>"+
		//	"and only like <strong>short-domain-name.herokuapp.com/YOURWORD</strong><br>"+
		//	"(you can't create links like this: <strong>YOURWORD.short-domain-name.herokuapp.com</strong>)",
		//	admin);

		 JArticle art5 = new JArticle(new Date(), null, null, "Redirecting",
			"FreeLink (owner admin) <a target=\"_blank\" href=\"short1.jca:10000/qqwwee\"><strong>https://short1.jca/qqwwee</strong></a> => google.com/search?q=java+spring<br>"+
			"PaidLink (owner admin) <a target=\"_blank\" href=\"short1.jca:10000/adminlink\"><strong>https://short1.jca/adminlink</strong></a> => www.linkedin.com/in/anton-isaev<br>"+
			"PaidLink (owner admin) <a target=\"_blank\" href=\"owesome.short1.jca:10000\"><strong>https://owesome.short1.jca</strong></a> => github.com/jcant<br>"+
			
			"FreeLink (owner user) <a target=\"_blank\" href=\"short2.jca:10000/aassdd\"><strong>https://short2.jca/aassdd</strong></a> => google.com/search?q=oop+principles<br>"+
			"PaidLink (owner user) <a target=\"_blank\" href=\"short2.jca:10000/userlink\"><strong>https://short2.jca/userlink</strong></a> => github.com/jcant<br>"+
			"PaidLink (owner user) <a target=\"_blank\" href=\"super.short2.jca:10000\"><strong>https://super.short2.jca</strong></a> => www.linkedin.com/in/anton-isaev<br>",
			admin);


		 articleService.addArticle(art1);
		 articleService.addArticle(art2);
		 articleService.addArticle(art3);
		 //articleService.addArticle(art4);
		 articleService.addArticle(art5);

		JAdvertising adv1 = new JAdvertising("jCant Graduate Project", null, null, "Link Management",
				"Project sources: " +
				"<a target=\"_blank\" href=\"https://github.com/jcant/JLinkManagement\"> GitHub</a>");

		JAdvertising adv2 = new JAdvertising("prog.kiev.ua", null, null, "Java Courses",
				"excellent Java Academy<br>" +
				"<a target=\"_blank\" href=\"https://prog.kiev.ua/\">https://prog.kiev.ua</a>");

		advService.addAdvertising(adv1);
		advService.addAdvertising(adv2);

		JRootLink rl1 = new JRootLink("short1.jca", true);
		JRootLink rl2 = new JRootLink("short2.jca", false);

		rlinkService.addRootLink(rl1);
		rlinkService.addRootLink(rl2);

		Date dstart = JDate.incDay(JDate.setTime(new Date(), "0:0:1"), -1);
		Date dfinish = JDate.incMonth(JDate.setTime(new Date(), "0:0:1"), 2);
		JLink l1 = new JLink("short1.jca/qqwwee", "google.com/search?q=java+spring", admin, dstart, dfinish, true, true);
		JLink l2 = new JLink("short1.jca/adminlink", "www.linkedin.com/in/anton-isaev", admin, dstart, dfinish, true, false);
		JLink l3 = new JLink("owesome.short1.jca", "github.com/jcant", admin, dstart, dfinish, true, false);
		
		JLink l4 = new JLink("short2.jca/aassdd", "google.com/search?q=oop+principles", ouser, dstart, dfinish, true, true);
		JLink l5 = new JLink("short2.jca/userlink", "github.com/jcant", ouser, dstart, dfinish, true, false);
		JLink l6 = new JLink("super.short2.jca", "www.linkedin.com/in/anton-isaev", ouser, dstart, dfinish, true, false);

		linkService.addLink(l1);
		linkService.addLink(l2);
		linkService.addLink(l3);
		linkService.addLink(l4);
		linkService.addLink(l5);
		linkService.addLink(l6);
	}
	


    @Bean
    public UrlBasedViewResolver setupViewResolver() {
    	UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry
        .addResourceHandler("/css/**")
        .addResourceLocations("/resources/css/");

    	registry
    	.addResourceHandler("/images/**")
        .addResourceLocations("/resources/images/");

    	registry
    	.addResourceHandler("/js/**")
        .addResourceLocations("/resources/js/");

    	registry
    	.addResourceHandler("/bootstrap/**")
        .addResourceLocations("/resources/bootstrap/");
    }


    @Bean
    public UserDetailsService userDetailsService(){
        return new JUserDetailServiceImpl();
    }

    @Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
    	RequestMappingHandlerMapping handlerMapping = new JDomainRequestMappingHandlerMapping();
		handlerMapping.setOrder(0);
		handlerMapping.setInterceptors();
		return handlerMapping;
	}
    
    @Bean
    public Logger loggerService(){
        return LogManager.getLogger();
    }

}
