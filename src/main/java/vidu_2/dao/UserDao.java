package vidu_2.dao;

import vidu_2.model.User;

public interface UserDao {

	User get(String username);
	void insert(User user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	
	User getByEmail(String email);
	void updatePassword(String email, String newPassword);
}
