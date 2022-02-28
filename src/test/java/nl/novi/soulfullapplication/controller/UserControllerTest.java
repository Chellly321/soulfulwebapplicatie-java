package nl.novi.soulfullapplication.controller;

import nl.novi.soulfullapplication.configuration.TokenUtil;
import nl.novi.soulfullapplication.dto.LoginDto;
import nl.novi.soulfullapplication.dto.Token;
import org.springframework.http.HttpStatus;
import nl.novi.soulfullapplication.model.User;
import nl.novi.soulfullapplication.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UserControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenUtil jwtTokenUtil;

    @MockBean
    private UserService userService;

    @Autowired
    UserController userController;

    @Test
    public void testLogin(){
        //ARRANGE
        LoginDto loginDto = new LoginDto("username", "password");
        Authentication authentication = Mockito.mock(Authentication.class);
        String token = "dummy";
        Mockito.when(authenticationManager.authenticate(any())).thenReturn(authentication);
        Mockito.when(jwtTokenUtil.generateToken(authentication)).thenReturn(token);

        //ACT
        ResponseEntity<?> result =  userController.login(loginDto);

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(token, ((Token)result.getBody()).getToken());
    }
    @Test
    public void testRegister() {
        //ARRANGE
        User user = new User();
        Mockito.when(userService.save(any())).thenReturn(user);

        //ACT
        ResponseEntity<User> result = userController.registerUser(user);

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(user, result.getBody());
    }

}
