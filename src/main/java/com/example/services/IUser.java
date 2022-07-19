package com.example.services;

import java.util.List;

import com.example.model.User;

public interface IUser {

	User addUser(User user);

	List<User> findAllUsers();

	User getUserByUserId(String userId);

	User updateUser(User u);

}
