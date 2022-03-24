package com.example.englishapp.Repository;

import com.example.englishapp.Entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    @Query(value = "SELECT * from token where user_id = :userID", nativeQuery = true)
    VerificationToken findByUserID(Long userID);
    @Query(value = "SELECT * from token where token = :token", nativeQuery = true)
    VerificationToken findbyToken(String token);
    @Query(value = "delete from token where id = :entityId", nativeQuery = true)
    void Delete(Long entityId);
}
