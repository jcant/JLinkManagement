package com.gmail.gm.jcant.JLinkManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JLinkManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(JLinkManagementApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(final UserService userService) {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... strings) throws Exception {
//				userService.addUser(new CustomUser("admin", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.ADMIN));
//				userService.addUser(new CustomUser("user", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.USER));
//			}
//		};
//	}
}
