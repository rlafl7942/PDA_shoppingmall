package com.example.shoppingmall.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
//@ToString
//@AllArgsConstructor
public class User {
    @Id // JPA 식별자
    private int id; // PK
    private String userId;
    private String pw;
    private String name;
    private String email;
    private String contact;

    public User(String userId, String pw, String name, String email, String contact) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public User fromDtoToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getPw(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getContact()
        );
    }

    // 오른쪽 마우스 + Generate + toString()
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
