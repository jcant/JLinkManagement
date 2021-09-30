package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import com.gmail.gm.jcant.JLinkManagement.Helpers.JRoleHelper;
import com.gmail.gm.jcant.JLinkManagement.JPA.JOperationInfo;
import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.*;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

	@Autowired
	private Logger logger;

	@Autowired
	private JUserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping(value = "/user/all")
	public List<JUser> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/user/{id}")
	public JUser getUserById(@PathVariable(value = "id") long id) throws JUserException {
		JUser user = null;
		user = userService.getUserById(id);
		return user;
	}

	@RequestMapping(value = "/user/getAdmins")
	public List<JUser> getAdmins() {
		return userService.getUsersByRole(JUserRole.ADMIN);
	}

	@RequestMapping(value = "/user/getUsers")
	public List<JUser> getUsers() {
		return userService.getUsersByRole(JUserRole.USER);
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public JOperationInfo<JUser> updateUser(@RequestParam String id, @RequestParam(required = false) String userName,
			@RequestParam(required = false) String userEmail, @RequestParam String currentPassword,
			@RequestParam(required = false) String newPassword, @RequestParam(required = false) String resetPassword,
			@RequestParam(required = false) String blockUser, @RequestParam(required = false) Integer userRole)
			throws JUserException {
		long lId = Long.parseLong(id);
		JUser dbUser = userService.getUserById(lId); // if user does't exist method will throw Exception
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// here is filtering operations on user by another user if they not admin
		if ((!JRoleHelper.isHasRole("ROLE_ADMIN", authUser.getAuthorities()))
				&& (!authUser.getUsername().equals(dbUser.getLogin()))) {
			throw new JUserException("Access deny to update another user");
		}
		// if dbUser and authUser differs, the authUser can be only admin (at this step)
		if ((!encoder.matches(currentPassword, dbUser.getPassword()))
				&& (!encoder.matches(currentPassword, authUser.getPassword()))) {
			return new JOperationInfo<JUser>("Wrong Password!", false);
		}
		if (userName != null) {
			dbUser.setName(userName);
		}
		if (userEmail != null) {
			dbUser.setEmail(userEmail);
		}
		if ((newPassword != null) || (resetPassword != null) || (blockUser != null) || (userRole != null)) {
			logger.warn("Sorry! It's a test server, Some user operations are disabled!");
			return new JOperationInfo<JUser>("Sorry! It's a test server, Some user operations are disabled!", false);
		}
//        if (newPassword != null){
//            dbUser.setResetPassword(false);
//            dbUser.setPassword(encoder.encode(newPassword));
//        }
//        if (resetPassword != null){
//            dbUser.setResetPassword(resetPassword.equals("true"));
//        }
//        if (blockUser != null){
//            dbUser.setBlocked(blockUser.equals("true"));
//        }
//        if (userRole != null){
//            if (userRole.intValue() == 0) dbUser.setRole(JUserRole.ADMIN);
//            if (userRole.intValue() == 1) dbUser.setRole(JUserRole.USER);
//        }

		userService.updateUser(dbUser);
		return new JOperationInfo<JUser>("User update success!", true);
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public JOperationInfo<JUser> addUser(@RequestParam String login, @RequestParam String password,
			@RequestParam String email) throws JUserException {

		logger.warn("Sorry! It's a test server, Some user operations are disabled!");
		return new JOperationInfo<JUser>("Sorry! It's a test server, Some user operations are disabled!", false);

//    	if (userService.existsByLogin(login)) {
//            throw new JUserException("User with login='"+login+"' is already exist!");
//        }
//
//        String passHash = encoder.encode(password);
//
//        JUser dbUser = new JUser(login, passHash, JUserRole.USER, email);
//        userService.addUser(dbUser);
//
//        return new JOperationInfo<JUser>("User create success!", true);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public JOperationInfo<JUser> deleteUser(@RequestParam long id) throws JUserException {

		logger.warn("Sorry! It's a test server, Some user operations are disabled!");
		return new JOperationInfo<JUser>("Sorry! It's a test server, Some user operations are disabled!", false);
		

//    	if (!userService.existsById(id)){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.OK);
	}

}
