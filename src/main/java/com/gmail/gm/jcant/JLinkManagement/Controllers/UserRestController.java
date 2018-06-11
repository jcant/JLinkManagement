package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserException;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
public class UserRestController {
    @Autowired
    private JUserService userService;
    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/users")
    public List<JUser> getUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/users/{id}")
    public JUser getUserById(@PathVariable(value = "id") long id) {
    	JUser user = null;

        try {
            user = userService.getUserById(id);
        } catch (JUserException e) {
            e.printStackTrace();
        }

        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestParam String login,
                                    	@RequestParam String password,
                                    	@RequestParam(required = false) String email) {
        if (userService.existsByLogin(login)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String passHash = encoder.encode(password);

        JUser dbUser = new JUser(login, passHash, JUserRole.USER, email);
        userService.addUser(dbUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestParam long id){
        if (!userService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
