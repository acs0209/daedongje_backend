package com.mysite.sbb.photoBoard.PhotoController.questionController;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PhotoQuestionForm {

    @NotEmpty(message = "아이디는 필수항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=200)
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;

    private String filename;

    private String filepath;

}