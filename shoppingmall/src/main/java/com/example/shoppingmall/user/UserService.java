package com.example.shoppingmall.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public String join(User user) {
        userRepository.save(user);

        return "";
    }

    public String check(String userId) {
        return userRepository.check(userId);
    }

    public boolean checkDuplicateId(String userId) {
        User existUser = userRepository.findByUserId(userId);
        if (existUser == null)
            return false;
        else
            return true;
    }

    public int login(User user) {
        return userRepository.login(user);
    }

//    public void makeConnection() {
//        userRepository.makeConnection();
//    }
}
