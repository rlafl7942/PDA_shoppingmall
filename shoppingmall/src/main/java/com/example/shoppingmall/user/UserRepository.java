package com.example.shoppingmall.user;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
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

    @Transactional
    public String save(User user) {
        entityManager.persist(user);

        User savedUser = entityManager.find(User.class, user.getId());

//        userTable.put(user.getUserId(), user);
//
//        User savedUser = userTable.get(user.getUserId());
        return savedUser.getUserId();
    }

    public String check(String userId) {
        User savedUser = userTable.get(userId);
        if (savedUser == null)
            return "failed";
        return "ok";
    }

    public User findById(String userId) {
        return userTable.get(userId);
    }
}
