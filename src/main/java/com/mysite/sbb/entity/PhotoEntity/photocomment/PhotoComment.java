package com.mysite.sbb.entity.PhotoEntity.photocomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysite.sbb.entity.PhotoEntity.photoquestion.PhotoQuestion;
import com.mysite.sbb.entity.PhotoEntity.photoanswer.PhotoAnswer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class PhotoComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotNull(message="내용은 필수 항목입니다.")
    @Size(min=1, message="내용은 최소 한 글자 이상이어야 합니다.")
    private String content;

    private String date;

    @Size(min=1,message="닉네임은 한 글자 이상이어야 합니다.")
    private String username;

    @JsonIgnore
    @NotNull(message="비밀번호는 필수 항목입니다.")
    @Size(min=4, max=50, message="비밀번호는 네 자리 이상이어야 합니다.")
    private String password;

    @JsonIgnore
    @ManyToOne
    private PhotoQuestion photoQuestion;

    @JsonIgnore
    @ManyToOne
    private PhotoAnswer photoAnswer;

    /*
     * 그리고 댓글을 수정하거나 삭제한 후에 질문 상세 페이지로 리다이렉트 하기 위해서는
     * 댓글을 통해 질문의 id를 알아내는 getQuestionId 메서드가 필요 이후 진행할 댓글 수정, 삭제에서 필요한 기능
     * getQuestionId 메서드는 댓글을 통해 질문의 id 값을 리턴하는 메서드로 question 속성이 null 이 아닌 경우는
     * 질문에 달린 댓글이므로 this.question.getId() 값을 리턴하고 답변에 달린 댓글인 경우
     * this.answer.getQuestion().getId() 값을 리턴하다.
     * */
    public Long getQuestionId() {
        Long result = null;
        if (this.photoQuestion != null) {
            result = this.photoQuestion.getId();
        } else if (this.photoAnswer != null) {
            result = this.photoAnswer.getPhotoQuestion().getId();
        }
        return result;
    }

//    @ManyToOne
//    private SiteUser author;
}
