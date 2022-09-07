package com.testtask.service;

import com.testtask.model.User;

import java.util.List;


public interface UserService {
    User create(User role);
    User readById(Long id);
    User update(User role);
    void delete(Long id);
    List<User> getAll();
}
