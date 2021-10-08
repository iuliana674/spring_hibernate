package com.iuliana.dao;

import com.iuliana.model.User;

import java.util.Set;

public interface UserDao {
    public Set<User> getAllUsers();

    public User getUserById(int id);

    public int deleteUserById(int id);

    public int insertUser(User user);

    public int updateUser(User user);
}