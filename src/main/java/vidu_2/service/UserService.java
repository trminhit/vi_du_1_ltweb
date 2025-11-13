package vidu_2.service;

import vidu_2.model.User;

public interface UserService {
    User login(String username, String password); 
    User get(String username); 
    
    void insert(User user);
    boolean register(String username, String email, String password);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
}