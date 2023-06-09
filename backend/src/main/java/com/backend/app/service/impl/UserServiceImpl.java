package com.backend.app.service.impl;

import com.backend.app.exception.ResourceNotFoundException;
import com.backend.app.model.User;
import com.backend.app.repository.UserRepository;
import com.backend.app.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getUserByUserName(String username) {
        Optional<User> user = userRepository.findById(username);
        if(user.isPresent()){
            return user.get();
        }
        else {
            throw new ResourceNotFoundException("User","username",username);
        }

    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User updateUser(User user, String username) {
        return null;
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public List<User> addUsers(List<User> users) {
        return userRepository.saveAll(users);
    }
}
