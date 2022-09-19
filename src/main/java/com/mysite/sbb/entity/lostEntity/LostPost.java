package com.mysite.sbb.entity.lostEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class LostPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "제목은 필수 항목입니다.")
    @Size(min=1, max=20, message = "제목은 최소 한 글자 이상, 50글자 이하여야 합니다.")
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


    @Pattern(regexp="^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$", message = "닉네임은 2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성해주세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "lostPost", cascade = CascadeType.REMOVE)
    private List<LostAnswer> lostAnswerList;

    @JsonIgnore
    @OneToMany(mappedBy = "lostPost")
    private List<LostComment> lostCommentList;

}

