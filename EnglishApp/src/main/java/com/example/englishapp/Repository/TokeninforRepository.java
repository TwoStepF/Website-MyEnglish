package com.example.englishapp.Repository;

import com.example.englishapp.Entity.TokenInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokeninforRepository extends JpaRepository<TokenInfor, Long> {
    @Query(value = "SELECT * from tokeninfor where username = :subject and time = :issuedAt", nativeQuery = true)
    Optional<TokenInfor> findByUsernameAndDate(Date issuedAt, String subject);

    void deleteByUsername(String username);
}
