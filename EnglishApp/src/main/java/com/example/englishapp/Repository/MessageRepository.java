package com.example.englishapp.Repository;

import com.example.englishapp.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * from (SELECT * from message order by id desc limit 20) as a order by id", nativeQuery = true)
    List<Message> get20mess();
}
