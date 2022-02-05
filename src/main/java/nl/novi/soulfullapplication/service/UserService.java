package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.model.User;
import nl.novi.soulfullapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
