package com.example.shoppingmall.user;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

//    @Autowired
//    DataSource dataSource;

    @Autowired
    EntityManager entityManager;

//    public void makeConnection() { // DB Connection
//        DataSourceUtils.getConnection(dataSource);
//    }

    private Map<String, User> userTable = new HashMap<>();


    public void save(User user) {
        entityManager.persist(user);

//        User savedUser = entityManager.find(User.class, user.getId());

//        userTable.put(user.getUserId(), user);
//
//        User savedUser = userTable.get(user.getUserId());
//        return savedUser.getUserId();
    }

    public String check(String userId) {
        User savedUser = userTable.get(userId);
        if (savedUser == null)
            return "failed";
        return "ok";
    }

    public User findByUserId(String userId) {
        String jpql = "SELECT m FROM User As m WHERE m.userId = :userId";

        return entityManager.createQuery(jpql, User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public int login(User user) {
        User loginUser = entityManager.find(User.class, user);
        if (loginUser == null) return 0;
        else return loginUser.getId();
    }
}
