package com.example.shoppingmall.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public String join(User user) {
        return userRepository.save(user);
    }

    public String check(String userId) {
        return userRepository.check(userId);
    }

    public boolean checkDuplicateId(String userId) {
        User existUser = userRepository.findById(userId);
        if (existUser == null)
            return false;
        else
            return true;
    }

//    public void makeConnection() {
//        userRepository.makeConnection();
//    }
}
