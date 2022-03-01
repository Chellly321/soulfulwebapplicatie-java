package nl.novi.soulfullapplication.controller;

import nl.novi.soulfullapplication.configuration.TokenUtil;
import nl.novi.soulfullapplication.dto.Token;
import nl.novi.soulfullapplication.dto.LoginDto;
import nl.novi.soulfullapplication.model.User;
import nl.novi.soulfullapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "users/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws AuthenticationException {
        // validate username and password
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        // if validation result in success, set the authentication object to context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate a jwt token from authentication object
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new Token(token));
    }

    @RequestMapping(value = "users/register", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(user);
    }
    @GetMapping("users/")
    public ResponseEntity<List<User>> findAll(@PathVariable User user){
      List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User has been deleted!");
    }
}
