package com.gmail.gm.jcant.JLinkManagement;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLinkService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUserService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JlinkUserRole;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JLinkManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(JLinkManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(final JLinkUserService userService, final JLinkService linkService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				JLinkUser admin = new JLinkUser("admin", "$2a$10$mvuMNa9iOkNJK1LyWLPj9uh.xaICWGjC78iRZkkdF9auHDjZLbjx.", JlinkUserRole.ADMIN);
				JLinkUser user = new JLinkUser("user", "$2a$10$mvuMNa9iOkNJK1LyWLPj9uh.xaICWGjC78iRZkkdF9auHDjZLbjx.", JlinkUserRole.USER);
				
				userService.addUser(admin);
				userService.addUser(user);
				
				linkService.addLink(new JLink("http://u1.short2.jca:8080/", "http://google.com", admin, new Date(), new Date()));
				linkService.addLink(new JLink("http://u2.short2.jca:8080/", "http://gmail.com", admin, new Date(), new Date()));
				linkService.addLink(new JLink("q3.com", "jcant.linkedin.com", admin, new Date(), new Date()));
				
				linkService.addLink(new JLink("u1.com", "user.facebook.com", user, new Date(), new Date()));
				linkService.addLink(new JLink("u2.com", "user.twitter.com", user, new Date(), new Date()));
				
				//non unique url - must throws an exception
				//linkService.addLink(new JLink("u2.com", "user.linkedin.com", user, new Date(), new Date()));
			}
		};
	}
}
