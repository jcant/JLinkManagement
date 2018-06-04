package com.gmail.gm.jcant.JLinkManagement.Controllers;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUser;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JLinkUserService;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JlinkUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private JLinkUserService userService;
    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/users")
    public List<JLinkUser> getUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/users/{id}")
    public JLinkUser getUserById(@PathVariable(value = "id") long id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestParam String login,
                                    @RequestParam String password,
                                    @RequestParam(required = false) String email) {
        if (userService.existsByLogin(login)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String passHash = encoder.encode(password);

        JLinkUser dbUser = new JLinkUser(login, passHash, JlinkUserRole.USER, email);
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
