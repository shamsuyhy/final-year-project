package com.backend.app.service;

import com.backend.app.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User addUser(User user);
    User getUserByUserName(String username);
    User updateUser(User user, String username);

    void deleteUser(String username);
    List<User> addUsers(List<User> users);

    void assignOrders(List<Long> orderIds, String username);
}
