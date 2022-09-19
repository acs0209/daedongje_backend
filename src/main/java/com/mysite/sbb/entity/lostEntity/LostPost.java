package com.mysite.sbb.entity.lostEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class LostPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=2, max=20, message = "제목은 2글자 이상, 50글자 이하여야 합니다.")
    private String subject;

    @NotBlank(message = "내용은 필수 항목입니다.")
    @Size(min=1, max=200, message = "내용은 최소 한 글자 이상, 200자 이하여야 합니다.")
    private String content;

    @Column
    private String createDate;

    @NotNull
    @Column(columnDefinition = "BIT")
    private Boolean isLost;   // 분실, 발견

    private String filename;

    private String filepath;

    @Size(min=4, max=15, message = "닉네임은 네 글자 이상, 15 글자 이하여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min=4, max=60, message = "비밀번호는 네 자리 이상, 20자리 이하여야 합니다.")
    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "lostPost", cascade = CascadeType.REMOVE)
    private List<LostAnswer> lostAnswerList;

    @JsonIgnore
    @OneToMany(mappedBy = "lostPost")
    private List<LostComment> lostCommentList;

}

