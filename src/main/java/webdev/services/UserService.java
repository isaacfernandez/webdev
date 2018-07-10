package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.User;
import webdev.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UserService {
    @Autowired
    UserRepository userRepository;


    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getUsername() +":"+ user.getPassword());
        return userRepository.save(user);
    }

    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        Optional<User> data = userRepository.findById(userId);
        if(data.isPresent()) {
            return data.get();
        }
        return null;
    }
}

