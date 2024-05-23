package com.example.shoppingmall.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;
    UserJPARepository userJPARepository;

//    @Transactional
//    public String join(User user) {
//        userRepository.save(user);
//
//        return userRepository
//                .findByUserId(user.getUserId())
//                .getUserId();
//    }
    @Transactional
    public String join(User user) {
        userJPARepository.save(user);
        return userJPARepository.findByUserId(user.getUserId())
                .map(User::getUserId)
                .orElseThrow(() -> new RuntimeException("UserId not found" + user.getUserId()));
    }

    public boolean checkDuplicateId(String userId) {
        return userJPARepository.findByUserId(userId).isPresent();
    }

    public int login(User user) {
        return userRepository.login(user);
    }

//    public void makeConnection() {
//        userRepository.makeConnection();
//    }
}
