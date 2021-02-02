package com.needle.library.controllers;

import com.needle.library.models.Lending;
import com.needle.library.models.User;
import com.needle.library.repository.LendingRepository;
import com.needle.library.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    final UserRepository userRepository;
    final LendingRepository lendingRepository;

    public UserController(UserRepository userRepository, LendingRepository lendingRepository) {
        this.userRepository = userRepository;
        this.lendingRepository = lendingRepository;
    }

    public User getUserById(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new Exception("User with Id " + userId + " not found");
        }
        return user.get();
    }

    @GetMapping("/users")
    public List<User> listAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserDetails(@PathVariable(name = "userId") Long userId) throws Exception {
        return getUserById(userId);
    }

    public Lending getUserLendingFromId(User user, Long lendingId) throws Exception {

        Optional<Lending> lending = lendingRepository.findById(lendingId);
        if (!lending.isPresent() || !lending.get().getUser().equals(user)) {
            throw new Exception("This lending id " + lendingId + " is not associated with user " + user.getId());
        }
        return lending.get();
    }

    @GetMapping("/{userId}/lending/{lendingId}")
    public Lending getLendingBook(@PathVariable(name = "userId") Long userId,
                                     @PathVariable(name = "lendingId") Long lendingId) throws Exception {
        User user = getUserById(userId);
        return getUserLendingFromId(user, lendingId);
    }

    @GetMapping("/{userId}/lendings")
    public List<Lending> getAllLendingForUser(@PathVariable(name = "userId") Long userId) throws Exception {
        User user = getUserById(userId);
        return lendingRepository.findAllByUser(user);
    }

}
