package com.gmail.gm.jcant.JLinkManagement;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUserService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JlinkUserRole;
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
	public CommandLineRunner demo(final JLinkUserService userService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				userService.addUser(new JLinkUser("admin", "$2a$10$mvuMNa9iOkNJK1LyWLPj9uh.xaICWGjC78iRZkkdF9auHDjZLbjx.", JlinkUserRole.ADMIN));
				userService.addUser(new JLinkUser("user", "$2a$10$mvuMNa9iOkNJK1LyWLPj9uh.xaICWGjC78iRZkkdF9auHDjZLbjx.", JlinkUserRole.USER));
			}
		};
	}
}
