package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.model.Role;
import nl.novi.soulfullapplication.model.User;
import nl.novi.soulfullapplication.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private RoleService roleService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void testLoadUserByUsername() {
        String username = "Michelly";
        Mockito.when(userRepository.findByUsername(username)).thenReturn(null);

        //ACT
        try {
            userService.loadUserByUsername("Michell");
        } catch (Exception e) {
            //ASSERT
            Assertions.assertEquals("Invalid username or password.", e.getMessage());
        }
    }
    @Test
    public void testFindAll() {
        User user = new User();
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        //ACT
        List<User> result = userService.findAll();

        //ASSERT
        Assertions.assertEquals(1, result.size());
    }


    @Test
    public  void testSave() {
        //ARRANGE
        User user = new User();
        user.setPassword("dummypass");
        Mockito.when(userRepository.findByUsername(any())).thenReturn(null);
        Mockito.when(roleService.findByName(any())).thenReturn(new Role());
        Mockito.when(userRepository.save(any())).thenReturn(user);

        //ACT
        User result = userService.save(user);

        //ASSERT
        Assertions.assertEquals(user, result);
    }

    @Test
    public  void testSaveThrowsExceptionWhenUserAlreadyPresent() {
        //ARRANGE
        User user = new User();
        user.setPassword("dummypass");
        Mockito.when(userRepository.findByUsername(any())).thenReturn(user);

        //ACT
        try {
            userService.save(user);
        }catch (Exception e) {
            //ASSERT
            Assertions.assertEquals("USERNAME IS ALREADY TAKEN!!", e.getMessage());
        }
    }

    @Test
    public  void testSaveThrowsExceptionWhenPasswordIsWrong() {
        //ARRANGE
        User user = new User();
        user.setPassword("dum");
        Mockito.when(userRepository.findByUsername(any())).thenReturn(null);

        //ACT
        try {
            userService.save(user);
        }catch (Exception e) {
            //ASSERT
            Assertions.assertEquals("PASSWORD SHOULD BE OF LENGTH IN BETWEEN 6 and 12", e.getMessage());
        }
    }
}
