package com.mysite.sbb.entity.lostEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "내용은 필수 항목입니다.")
    @Size(min=1, max=200, message = "내용은 최소 한 글자 이상, 200자 이하여야 합니다.")
    private String content;

    private String createDate;

    @Size(min=1, message = "닉네임은 한 글자 이상이어야 합니다.")
    @NotNull(message = "닉네임은 필수 항목입니다.")
    private String username;

    @NotNull(message = "비밀번호는 필수 항목입니다.")
    @Size(min=4, max=150, message = "비밀번호는 네 자리 이상이어야 합니다.")
    private String password;

    @JsonIgnore
    @ManyToOne
    private LostPost lostPost;

    @JsonIgnore
    @ManyToOne
    private LostAnswer lostAnswer;

}