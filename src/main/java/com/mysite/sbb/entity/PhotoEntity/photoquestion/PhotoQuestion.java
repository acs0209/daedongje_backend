package com.mysite.sbb.entity.PhotoEntity.photoquestion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysite.sbb.entity.PhotoEntity.photoanswer.PhotoAnswer;
import com.mysite.sbb.entity.PhotoEntity.photocomment.PhotoComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PhotoQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    @NotNull(message = "제목은 필수 항목입니다.")
    @Size(min = 2, max = 50, message = "제목은 2글자 이상, 50글자 이하여야 합니다.")
    private String subject;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "내용은 필수 항목입니다.")
    @Size(min = 1, max = 200, message = "내용은 최소 한 글자 이상, 200자 이하여야 합니다.")
    private String content;

    private String date; //date변경

    @Size(min = 1, max = 15, message = "닉네임은 한 글자 이상, 15글자 이하여야 합니다.")
    private String username;

    @NotNull(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 4, max = 60, message = "비밀번호는 네 자리 이상, 20자리 이하어야 합니다.")
    private String password;

    private String filename; // 파일 이름

    private String filepath; // 파일 경로

    /*
     * 질문 하나에는 여러개의 답변이 작성될 수 있다. 이때 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해서
     * @OneToMany의 속성으로 cascade = CascadeType.REMOVE를 사용했다.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "photoQuestion", cascade = CascadeType.REMOVE)
    private List<PhotoAnswer> photoAnswerList;

    @JsonIgnore
    @OneToMany(mappedBy = "photoQuestion", cascade = CascadeType.REMOVE)
    private List<PhotoComment> photoCommentList;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;
}
