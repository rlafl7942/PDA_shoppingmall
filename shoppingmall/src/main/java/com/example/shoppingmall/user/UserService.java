package com.example.shoppingmall.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    @Transactional
    public String join(User user) {
        userRepository.save(user);

        return userRepository
                .findByUserId(user.getUserId())
                .getUserId();
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
