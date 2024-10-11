package com.example.buoi6.repository;
import com.example.buoi6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
