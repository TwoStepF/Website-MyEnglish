package com.example.englishapp.Repository;

import com.example.englishapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * from user where username = :username", nativeQuery = true)
    User findByUsername(String username);
    @Query(value = "SELECT * from user where email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);
    @Query(value = "SELECT * from user where userid = :id", nativeQuery = true)
    User findByUserID(Long id);
}
