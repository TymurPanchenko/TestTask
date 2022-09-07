package com.testtask.service.impl;

import com.testtask.model.User;
import com.testtask.repository.UserRepository;
import com.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User readById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.get();
    }

    @Override
    public User create(User user) {
        try {
            return userRepository.save(user);
        } catch (IllegalArgumentException e) {
            throw new NullPointerException("User cannot be 'null'");
        }
    }

    @Override
    public User update(User user) {
        if (user != null) {
            User oldUser = readById(user.getId());
            if (oldUser != null) {
                return userRepository.save(user);
            }
        }
        throw new NullPointerException("User cannot be 'null'");
    }

    @Override
    public void delete(Long id) {
        User user = readById(id);
        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new NullPointerException("User cannot be 'null'");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }
}
