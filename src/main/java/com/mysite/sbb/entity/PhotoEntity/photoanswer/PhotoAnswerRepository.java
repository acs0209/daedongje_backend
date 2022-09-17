package com.mysite.sbb.entity.PhotoEntity.photoanswer;

import com.mysite.sbb.entity.PhotoEntity.photoquestion.PhotoQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoAnswerRepository extends JpaRepository<PhotoAnswer, Long> {

    Page<PhotoAnswer> findAll(Pageable pageable);

    // Pageable 객체를 입력으로 받아 Page<Answer> 타입 객체를 리턴하는 findAll 메서드
    // 답변은 질문에 달려있다 따라서 질문에 달려있는 답변들을 페이징 처리하는거니 질문을 통해
    // 데이터를 조회할려한다
    Page<PhotoAnswer> findAllByPhotoQuestion(PhotoQuestion photoQuestion, Pageable pageable);
}
