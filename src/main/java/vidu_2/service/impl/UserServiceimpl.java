package vidu_2.service.impl;

import vidu_2.dao.UserDao;
import vidu_2.dao.impl.UserDaoimpl;
import vidu_2.model.User;
import vidu_2.service.UserService;

public class UserServiceimpl implements UserService {
	UserDao userDao = new UserDaoimpl();

	public User login(String username, String password) {
		User user = this.get(username);
		System.out.println("User tìm thấy trong DB: " + user);
		if (user != null && password.equals(user.getPassword())) { 
			return user;
		}
		return null;
	}
	@Override
	public void insert(User user) {
	    userDao.insert(user);
	}

	public boolean register(String email, String password, String username, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		userDao.insert(new User(email, username, fullname, password, null, 5, phone, date));
		return true;
	}
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
		}
	
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}
	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	public User get(String username) {
		return userDao.get(username);
	}
	
	@Override
	public User getByEmail(String email) {
	    return userDao.getByEmail(email);
	}

	@Override
	public void updatePassword(String email, String newPassword) {
	    userDao.updatePassword(email, newPassword);
	}
}