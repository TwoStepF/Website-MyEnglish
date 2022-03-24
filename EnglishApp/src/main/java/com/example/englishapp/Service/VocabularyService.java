package com.example.englishapp.Service;

import com.example.englishapp.Dto.StatusData;
import com.example.englishapp.Dto.StatusP;
import com.example.englishapp.Dto.VocabularyDTO;
import com.example.englishapp.Entity.User;
import com.example.englishapp.Entity.Vocabulary;
import com.example.englishapp.Repository.UserRepository;
import com.example.englishapp.Repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VocabularyService {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VocabularyRepository vocabularyRepository;

    public StatusP creatVocabulary(VocabularyDTO vocabularyDTO){
        try {
            if(vocabularyDTO.getEnglishmean() == null || vocabularyDTO.getVietnamesemean()== null || vocabularyDTO.getType()== null){
                return new StatusP("false", "Bạn chưa nhập đủ dữ liệu", "");
            }
            if(!vocabularyDTO.getType().equals("Danh từ") && !vocabularyDTO.getType().equals("Động từ") && !vocabularyDTO.getType().equals("Tính từ") && !vocabularyDTO.getType().equals("Trạng từ")){
                return new StatusP("false", "Nhập sai định dạng từ", "");
            }
            Vocabulary vocabulary = vocabularyRepository.findByEnglishMeaningAndType(vocabularyDTO.getEnglishmean(), vocabularyDTO.getType());
            if(vocabulary == null) {
                User logInUser = authService.getCurrentUser();
                Vocabulary Cvocabulary = new Vocabulary(vocabularyDTO.getEnglishmean(), vocabularyDTO.getType(), vocabularyDTO.getVietnamesemean(), logInUser);
                vocabularyRepository.save(Cvocabulary);
                return new StatusP("ok", "Thêm từ vựng thành công", "");
            }
            return new StatusP("false", "Đã tồn tại từ này trong từ điển", "");
        }catch (Exception e){
            return new StatusP("false", "Lỗi", "");
        }
    }

    public List<VocabularyDTO> getAllVocabulary() {
        User logInUser =  authService.getCurrentUser();
        List<Vocabulary> vocabulary = vocabularyRepository.findvocabularyByUsername(logInUser.getUserID());
        return vocabulary.stream().map(this::MapDataToDTO).collect(Collectors.toList());
    }

    public VocabularyDTO MapDataToDTO(Vocabulary vocabulary){
        VocabularyDTO vocabularyDTO = new VocabularyDTO();
        vocabularyDTO.setId(vocabulary.getVocabularyID());
        vocabularyDTO.setEnglishmean(vocabulary.getEnglishMeaning());
        vocabularyDTO.setVietnamesemean(vocabulary.getVietnameseMeaning());
        vocabularyDTO.setType(vocabulary.getType());
        return vocabularyDTO;
    }

    public List<StatusData> findVocabularyByEnglishMean(VocabularyDTO vocabularyDTO) {
        List<Vocabulary> vocabulary = vocabularyRepository.findByEnglish(vocabularyDTO.getEnglishmean());
        if(vocabulary.size() != 0)
            return vocabulary.stream().map(this::MapVocabularyToDTO).collect(Collectors.toList());
        StatusData statusData = new StatusData("false", "Không tồn tại trong từ điển, thêm mới ?", "", "", "");
        List<StatusData> statusDatas = new ArrayList<>();
        statusDatas.add(statusData);
        return statusDatas;
    }

    public List<StatusData> findVocabularyByVietnamemean(VocabularyDTO vocabularyDTO) {
        List<Vocabulary> vocabulary = vocabularyRepository.findByVietname(vocabularyDTO.getVietnamesemean());
        if(vocabulary.size() != 0)
            return vocabulary.stream().map(this::MapVocabularyToDTO).collect(Collectors.toList());
        StatusData statusData = new StatusData("false", "Không tồn tại trong từ điển, thêm mới ?", "", "", "");
        List<StatusData> statusDatas = new ArrayList<>();
        statusDatas.add(statusData);
        return statusDatas;
    }

    public StatusData MapVocabularyToDTO(Vocabulary vocabulary){
        StatusData statusData = new StatusData();
        statusData.setStatus("ok");
        statusData.setEnglishmean(vocabulary.getEnglishMeaning());
        statusData.setVietnamesemean(vocabulary.getVietnameseMeaning());
        statusData.setType(vocabulary.getType());
        return statusData;
    }

}
