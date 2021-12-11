package com.noname.simple_crud.service;

import com.noname.simple_crud.model.User;
import com.noname.simple_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(long id, User user) {
        Optional<User> userData = userRepository.findById(id);
        if (!userData.isPresent()) {
            return null;
        }
        User _user = userData.get();
        _user.setFirstName(user.getFirstName());
        _user.setLastName(user.getLastName());
        _user.setEmail(user.getEmail());
        _user.setDateOfBirth(user.getDateOfBirth());
        return userRepository.save(_user);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
