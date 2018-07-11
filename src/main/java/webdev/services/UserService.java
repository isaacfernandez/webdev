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
            System.out.println("User is unique, saving.");
            User newUser =  userRepository.save(user);
            session.setAttribute("currentUser", newUser.getId());
            return newUser;
        } else {
            System.out.println("user already exists");
            return null;
        }
    }

    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpSession session) {
        user = userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
        if (user != null) {
            session.setAttribute("currentUser", user.getId());
            System.out.println("Successful log in by " + user.getUsername());
            return user;
        } else {
            return null;
        }
    }

    @PostMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/profile")
    public User getUserSession(HttpSession session) {
        int userId = (int) session.getAttribute("currentUser");
        return findUserById(userId);
    }

    @PutMapping("/api/profile")
    public User updateProfile(@RequestBody User user, HttpSession session) {
        try {
            int userId = (int) session.getAttribute("currentUser");
            User u = findUserById(userId);
            u.setEmail(user.getEmail());
            u.setPhone(user.getPhone());
            u.setDateOfBirth(user.getDateOfBirth());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            userRepository.save(u);
            return u;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }


    @GetMapping("/api/user/{username}")
    public User findUserByUsername(@PathVariable("username") String username) {
        return  userRepository.findUserByUsername(username);
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

    @PutMapping("/api/user/{userId}")
    public User updateUser(@PathVariable("userId") int userId, @RequestBody User uUser){
        Optional<User> data = userRepository.findById(userId);
        if(data.isPresent()) {
            User user = data.get();
            user.setFirstName(uUser.getFirstName());
            user.setLastName(uUser.getLastName());
            user.setEmail(uUser.getEmail());
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {

        userRepository.deleteById(userId);
    }

 }

