package com.gmail.gm.jcant.JLinkManagement;

import com.gmail.gm.jcant.JLinkManagement.Controllers.MainController;
import com.gmail.gm.jcant.JLinkManagement.Controllers.RequestFilter;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:config.properties")
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig {
    @Value("${hibernate.dialect}")
    private String sqlDialect;

    @Value("${hbm2ddl.auto}")
    private String hbm2dllAuto;

    @Bean
    public MainController getController(){
        return new MainController();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
            (DataSource dataSource, JpaVendorAdapter jpaVendeorAdapter)
    {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendeorAdapter);
        entityManagerFactory.setJpaProperties(additionalProperties());
        entityManagerFactory.setPackagesToScan("com.gmail.gm.jcant.JLinkManagement");
        return entityManagerFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabasePlatform(sqlDialect);

        return adapter;
    }
    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2dllAuto);
        return properties;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new JLinkUserDetailServiceImpl();
    }

    @Bean
    public RequestFilter getServletFilter() {
        System.out.println("in GET SERVLET FILTER**********************************");
        return new RequestFilter();
    }

}

