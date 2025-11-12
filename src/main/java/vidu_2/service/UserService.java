package vidu_2.service;

import vidu_2.model.User;

public interface UserService {
    User login(String username, String password); 
    User get(String username); 
}