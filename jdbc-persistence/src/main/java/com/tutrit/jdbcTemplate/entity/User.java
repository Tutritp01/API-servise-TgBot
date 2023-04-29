package com.tutrit.jdbcTemplate.entity;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    Long userId;
    String name;
    String phoneNumber;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

}
