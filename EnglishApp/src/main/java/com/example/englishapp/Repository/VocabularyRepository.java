package com.example.englishapp.Repository;

import com.example.englishapp.Entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
    @Query(value = "select * from vocabulary where user_id = :id order by vocabularyid DESC", nativeQuery = true)
    List<Vocabulary> findvocabularyByUsername(Long id);
    @Query(value = "select * from vocabulary where english_meaning LIKE :english ", nativeQuery = true)
    List<Vocabulary> findByEnglish(String english);
    @Query(value = "select * from vocabulary where vietnamese_mean LIKE :vietnamesemean ", nativeQuery = true)
    List<Vocabulary>findByVietname(String vietnamesemean);
    @Query(value = "select * from vocabulary where english_meaning LIKE :english and type = :type ", nativeQuery = true)
    Vocabulary findByEnglishMeaningAndType(String english, String type);
}
