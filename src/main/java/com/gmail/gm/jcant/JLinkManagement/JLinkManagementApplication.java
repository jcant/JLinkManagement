package com.gmail.gm.jcant.JLinkManagement;

import com.gmail.develop.jcant.JDate;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserRole;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Article.JArticle;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertising;
import java.util.Date;

import com.gmail.gm.jcant.JLinkManagement.DomainRouting.JDomainRequestMappingHandlerMapping;
import com.gmail.gm.jcant.JLinkManagement.JPA.Advertising.JAdvertisingService;
import com.gmail.gm.jcant.JLinkManagement.JPA.Article.JArticleService;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.RootLink.JRootLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserDetailServiceImpl;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpringBootApplication
@EnableWebMvc
public class JLinkManagementApplication implements WebMvcConfigurer{

	@Autowired
    private Logger logger;
	
//    @Value("${hibernate.dialect}")
//    private String sqlDialect;
//
////    @Value("${hbm2ddl.auto}")
////    private String hbm2dllAuto;
//
//    @Value("${spring.datasource.url}")
//	private String dbUrl;
//    
//    @Value("${spring.datasource.username}")
//	private String userName;
//    @Value("${spring.datasource.password}")
//	private String passWord;
//
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
				logger.info("WE ARE ON INIT()!!!");
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
			"In view of hosting restrictions, the redirect works only on references of the type http://sitename.com/your_link</br>"+
			"</br>"+
			"You can register a new user or log in as an administrator or ordinaly user:",
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

		 JArticle art4 = new JArticle(new Date(), null, null, "Redirecting",
			"Due to \"heroku\" limitations You may create links based on only one root link: <strong>short-domain-name.herokuapp.com</strong><br>"+
			"and only like <strong>short-domain-name.herokuapp.com/YOURWORD</strong><br>"+
			"(you can't create links like this: <strong>YOURWORD.short-domain-name.herokuapp.com</strong>)",
			admin);

		 JArticle art5 = new JArticle(new Date(), null, null, "Redirecting",
			"FreeLink (owner admin) <a target=\"_blank\" href=\"https://short-domain-name.herokuapp.com/qqwwee\"><strong>https://short-domain-name.herokuapp.com/qqwwee</strong></a> => google.com?search=qwerty<br>"+
			"FreeLink (owner user) <a target=\"_blank\" href=\"https://short-domain-name.herokuapp.com/aassdd\"><strong>https://short-domain-name.herokuapp.com/aassdd</strong></a> => yahoo.com<br>"+
			"PaidLink (owner admin) <a target=\"_blank\" href=\"https://short-domain-name.herokuapp.com/superadminlink\"><strong>https://short-domain-name.herokuapp.com/superadminlink</strong></a> => gmail.com<br>"+
			"PaidLink (owner user) <a target=\"_blank\" href=\"https://short-domain-name.herokuapp.com/superuserlink\"><strong>https://short-domain-name.herokuapp.com/superuserlink</strong></a> => github.com<br>",
			admin);


		 articleService.addArticle(art1);
		 articleService.addArticle(art2);
		 articleService.addArticle(art3);
		 articleService.addArticle(art4);
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
		JLink l1 = new JLink("short1.jca/qqwwee", "google.com", admin, dstart, dfinish, true, true);
		JLink l2 = new JLink("short1.jca/adminlink", "gmail.com", admin, dstart, dfinish, true, false);
		JLink l3 = new JLink("owesome.short1.jca", "gmail.com", admin, dstart, dfinish, true, false);
		JLink l4 = new JLink("short2.jca/aassdd", "yahoo.com", ouser, dstart, dfinish, true, true);
		JLink l5 = new JLink("short2.jca/userlink", "github.com", ouser, dstart, dfinish, true, false);
		JLink l6 = new JLink("super.short2.jca", "github.com", ouser, dstart, dfinish, true, false);

		linkService.addLink(l1);
		linkService.addLink(l2);
		linkService.addLink(l3);
		linkService.addLink(l4);
		linkService.addLink(l5);
		linkService.addLink(l6);
	}
	


//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
//        return new JpaTransactionManager(emf);
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory
//            (DataSource dataSource, JpaVendorAdapter jpaVendorAdapter)
//    {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(dataSource);
//        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
//        entityManagerFactory.setJpaProperties(additionalProperties());
//        entityManagerFactory.setPackagesToScan("com.gmail.gm.jcant.JLinkManagement");
//        return entityManagerFactory;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        //adapter.setShowSql(true);
//        //adapter.setDatabasePlatform(sqlDialect);
//        //adapter.setDatabase(Database.POSTGRESQL);
//    	//adapter.setGenerateDdl(true);
//        
//        adapter.setShowSql(true);
//        adapter.setDatabasePlatform(sqlDialect);
//
//        return adapter;
//    }
//    
//    private Properties additionalProperties() {
//        Properties properties = new Properties();
//        //properties.setProperty("hibernate.hbm2ddl.auto", hbm2dllAuto);
//        return properties;
//    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
    	logger.info("we are in setupViewResolver() *********** ");
    	UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	System.out.println("we are in addResourceHandlers() *********** ");
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
		logger.info("we are in requestMappingHandlerMapping() *********** ");
    	RequestMappingHandlerMapping handlerMapping = new JDomainRequestMappingHandlerMapping();
		handlerMapping.setOrder(0);
		handlerMapping.setInterceptors();
		return handlerMapping;
	}
    
    @Bean
    public Logger loggerService(){
        return LogManager.getLogger();
    }

//    @Bean
//	public DataSource dataSource()  {
//      HikariConfig config = new HikariConfig();
//      config.setJdbcUrl(dbUrl);
//      config.setUsername(userName);
//      config.setPassword(passWord);
//   
//      return new HikariDataSource(config);
//	}
}
