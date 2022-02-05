package nl.novi.soulfullapplication.controller;

import nl.novi.soulfullapplication.model.User;
import nl.novi.soulfullapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> adduser(@RequestBody User user) {
        User result = userService.addUser(user);
        return ResponseEntity.ok(result);
    }
}
