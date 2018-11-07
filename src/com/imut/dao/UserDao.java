package com.imut.dao;

import java.util.List;

import com.imut.domin.User;

public interface UserDao {
	public boolean add(User user);
	
	public boolean del(int userId);
	
	public User findById(int userId);
	
	public boolean update(User user);
	
	public List<User> findAll();
}
