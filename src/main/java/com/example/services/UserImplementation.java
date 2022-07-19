package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.exception.EmptyInputException;
import com.example.model.User;

@Service
public class UserImplementation implements IUser {
	@Autowired
	private UserDao ud;
	
	@Override
	public User addUser(User user) {
		List<User> ulist=ud.findAll();
		int count = (int)ulist.stream().filter(c->c.getMobileNumber().equals(user.getMobileNumber())).count();
		if(count==0) {
		return ud.save(user);
		}
		throw new EmptyInputException("610","User Already entered");
	}
	@Override
	public List<User> findAllUsers() {
		return ud.findAll();
	}
	@Override
	public User getUserByUserId(String userId) {
		if(ud.existsById(userId))
		return ud.findById(userId).get();
		throw new EmptyInputException("612", "UserId does not exist!");
	}
	
	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return ud.save(u);
	}
}
