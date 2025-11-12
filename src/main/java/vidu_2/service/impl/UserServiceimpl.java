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
        // So sánh mật khẩu (lưu ý: slide đang so sánh clear text, thực tế nên hash password)
        if (user != null && password.equals(user.getPassword())) { // [cite: 259]
            return user; 
        }
        return null; 
    }

    public User get(String username) {
        return userDao.get(username); 
    }
}