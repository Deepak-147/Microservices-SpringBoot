package com.ldeepak.springboot.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	// Talks to static list
	
	
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 3;
	
	static {
		users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(2, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(3, "Jim", LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(int id) {
		
		for (int i=0; i<users.size(); i++) {
			User user = users.get(i);
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	public void deleteById(int id) {
	
		for (int i=0; i<users.size(); i++) {
			User user = users.get(i);
			if (user.getId() == id) {
				users.remove(i);
			}
		}
	}
}
