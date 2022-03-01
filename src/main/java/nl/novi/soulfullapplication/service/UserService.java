package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.model.Role;
import nl.novi.soulfullapplication.model.User;
import nl.novi.soulfullapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // overriden method of UserDetailService;
        // takes username and search in database if user is present with given user name or not
        // if not throw exception
        // else it creates a object of User which is present in spring security
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    public User save(User user) {
        // take the username from user object and check in database if there
        // is any user present with that username
        User outputUser = userRepository.findByUsername(user.getUsername());
        if (outputUser != null) {
            throw new RuntimeException("USERNAME IS ALREADY TAKEN!!");
        }

        // check if the password is between 6 and 12 length
        if (user.getPassword().length() < 6
                 && user.getPassword().length() > 12) {
            throw new RuntimeException("PASSWORD SHOULD BE OF LENGTH IN BETWEEN 6 and 12");
        }

        // encode the password using bcryptencoder
        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        String encodePassword = bcryptEncoder.encode(password);
        user.setPassword(encodePassword);

        // get the user role object from database and set it to the present userobject
        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        user.setRoles(roleSet);
        // User user = userRepository.save(user);
        // return user;
        return userRepository.save(user);
    }
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
