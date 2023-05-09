package com.tutrit.springdata.repository;

import com.tutrit.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
