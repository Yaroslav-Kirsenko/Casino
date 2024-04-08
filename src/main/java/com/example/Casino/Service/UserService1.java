//package com.example.Casino.Service;
//
//import com.example.Casino.Model.User1;
//import com.example.Casino.Repo.UserRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class UserService1 {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    public User1 authenticate(String username, String password) {
//        User1 user = userRepository.findUserByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
//        return null;
//    }
//}
