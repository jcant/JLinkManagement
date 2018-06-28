package com.gmail.gm.jcant.JLinkManagement.Controllers.Rest;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public JUserOperationInfo updateUser(@RequestParam(required = false) String userName,
                                         @RequestParam(required = false) String userEmail,
                                         @RequestParam String currentPassword,
                                         @RequestParam(required = false) String newPassword,
                                         @PathVariable(value="id") long id) throws JUserException {

        JUser dbUser = userService.getUserById(id); //if user does't exist method throws Exeption

        if (!encoder.matches(currentPassword, dbUser.getPassword())){
            return new JUserOperationInfo("Wrong Password!", false);
        }

        if (userName != null){
            dbUser.setName(userName);
        }
        if (userEmail != null){
            dbUser.setEmail(userEmail);
        }

        if (newPassword != null){
            dbUser.setPassword(encoder.encode(newPassword));
        }

        userService.updateUser(dbUser);

        return new JUserOperationInfo("User update success!", true);
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
