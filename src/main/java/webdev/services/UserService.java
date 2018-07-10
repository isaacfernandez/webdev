package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.User;
import webdev.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class UserService {
    @Autowired
    UserRepository userRepository;

    //rewire register to this
    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getUsername() +":"+ user.getPassword());
        return userRepository.save(user);
    }

    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpSession session) {
        if (this.findUserByUsername(user.getUsername()) == null) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }


    @GetMapping("/api/user/{username}")
    public User findUserByUsername(@PathVariable("username") String username) {
        User data = userRepository.findUserByUsername(username);
            if(data == null) {
            return data;
        }
        return data;
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

