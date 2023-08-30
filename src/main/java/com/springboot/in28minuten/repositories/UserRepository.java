package com.springboot.in28minuten.repositories;

import com.springboot.in28minuten.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
