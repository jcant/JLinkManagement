package com.gmail.gm.jcant.JLinkManagement.Security;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JUserDetailServiceImpl userDetailsService;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                //front-end
                .antMatchers("/").permitAll()
                .antMatchers("/profile").hasAnyRole("USER", "ADMIN")
                .antMatchers("/freelinks").hasAnyRole("USER", "ADMIN")
                .antMatchers("/links").hasAnyRole("USER", "ADMIN")
                .antMatchers("/stats").hasAnyRole("USER", "ADMIN")
                .antMatchers("/articles").hasRole("ADMIN")
                .antMatchers("/promo").hasRole("ADMIN")
                .antMatchers("/rootlinks").hasRole("ADMIN")
                .antMatchers("/users").hasRole("ADMIN")
                
                //Buy PayedLinks
                .antMatchers("/payment").hasAnyRole("USER", "ADMIN")
                .antMatchers("/paymentconfirm").hasAnyRole("USER", "ADMIN")
                
                //REST Controllers:
                .antMatchers("/promo/getActual").hasAnyRole("USER", "ADMIN")
                .antMatchers("/articles/getActual").permitAll()// hasAnyRole("USER", "ADMIN")
                .antMatchers("/articles/**").hasRole("ADMIN")
                .antMatchers("/rootlinks/getActual").hasAnyRole("USER", "ADMIN")
                .antMatchers("/link/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/stats/**").hasAnyRole("USER", "ADMIN")
                
                //UserRestController
                .antMatchers("/users/add").permitAll()//.hasAnyRole("USER", "ADMIN") //?? is this necessary?
                .antMatchers("/users/update").hasAnyRole("USER", "ADMIN")
                .antMatchers("/users/**").hasRole("ADMIN") // /users/all (GET), /users/{id} (GET), /users/{id} (DELETE)
                
                //LoginController
                .antMatchers("/loginsuccess").hasAnyRole("USER", "ADMIN")
                
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")

                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/j_auth")
                .successForwardUrl("/loginsuccess")
                //.failureUrl("/login?error")
                .failureUrl("/")
                .usernameParameter("j_login")
                .passwordParameter("j_password")
                .permitAll()
                
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }


    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
