package com.mysite.sbb.entity.lostEntity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LostAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "내용은 필수 항목입니다.")
    @Size(min=1, max=200, message = "내용은 최소 한 글자 이상, 200자 이하여야 합니다.")
    private String content;

    private String createDate;

    @Size(min=1, max=15, message = "닉네임은 한 글자 이상, 15 글자 이하여야 합니다.")
    @NotNull(message = "닉네임은 필수 항목입니다.")
    private String username;

    @NotNull(message = "비밀번호는 필수 항목입니다.")
    @Size(min=4, max=60, message = "비밀번호는 네 자리 이상, 20자리 이하여야 합니다.")
    private String password;

    @ManyToOne
    @JsonIgnore
    private LostPost lostPost;

    @JsonIgnore
    @OneToMany(mappedBy = "lostAnswer", cascade = CascadeType.REMOVE)
    private List<LostComment> lostCommentList;

}