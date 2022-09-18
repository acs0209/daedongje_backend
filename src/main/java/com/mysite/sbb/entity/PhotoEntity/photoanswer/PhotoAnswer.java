package com.mysite.sbb.entity.PhotoEntity.photoanswer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysite.sbb.entity.PhotoEntity.photocomment.PhotoComment;
import com.mysite.sbb.entity.PhotoEntity.photoquestion.PhotoQuestion;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class PhotoAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotNull(message="내용은 필수 항목입니다.")
    @Size(min=1, message="내용은 최소 한 글자 이상이어야 합니다.")
    private String content;

    @ManyToOne
    @JoinColumn
    private PhotoQuestion photoQuestion;

    @CreatedDate
    private String date;

    @NotNull(message="닉네임은 한 글자 이상이어야 합니다.")
    private String username;

    @NotNull(message="비밀번호는 필수 항목입니다.")
    @Size(min=4, max=50, message="비밀번호는 네 자리 이상이어야 합니다.")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "photoAnswer", cascade = CascadeType.REMOVE)
    private List<PhotoComment> photoCommentList;

}
