package com.backend.app.service.impl;

import com.backend.app.exception.ResourceNotFoundException;
import com.backend.app.model.Order;
import com.backend.app.model.User;
import com.backend.app.repository.OrderRepository;
import com.backend.app.repository.UserRepository;
import com.backend.app.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private OrderRepository orderRepository;

    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
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

    @Override
    public void assignOrders(List<Long> orderIds, String username) {
        User user= userRepository.getReferenceById(username);
        List<Order> orders =orderRepository.findAllById(orderIds);
        user.assignOrders(orders);
        userRepository.save(user);
    }
}
