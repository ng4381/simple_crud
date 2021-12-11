package com.noname.simple_crud.service;

import com.noname.simple_crud.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(long id);
    User updateUser(long id, User user);
    User create(User user);
    void deleteById(long id);
}
