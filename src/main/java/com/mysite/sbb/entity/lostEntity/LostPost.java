package com.mysite.sbb.entity.lostEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class LostPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=50, message = "제목은 2글자 이상, 50글자 이하여야 합니다.")
    private String subject;

    @NotNull(message = "내용은 필수 항목입니다.")
    @Size(min=1, message = "내용은 최소 한 글자 이상이어야 합니다.")
    private String content;

    @Column
    private String createDate;

    @NotNull
    private Boolean isLost;   // 분실, 발견

    private String filename;

    private String filepath;

    @Size(min=1, message = "닉네임은 한 글자 이상이어야 합니다.")
    private String username;

    @NotNull(message = "비밀번호는 필수 항목입니다.")
    @Size(min=4, max=50, message = "비밀번호는 네 자리 이상이어야 합니다.")
    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "lostPost", cascade = CascadeType.REMOVE)
    private List<LostAnswer> lostAnswerList;

    @JsonIgnore
    @OneToMany(mappedBy = "lostPost")
    private List<LostComment> lostCommentList;

}

