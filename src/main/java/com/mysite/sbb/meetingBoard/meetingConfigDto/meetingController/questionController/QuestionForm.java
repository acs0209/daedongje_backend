package com.mysite.sbb.meetingBoard.meetingConfigDto.meetingController.questionController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {


    @NotEmpty(message = "아이디는 필수항목입니다.")
    @Size(min=4, max=15)
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    @Size(min=4, max=60)
    private String password;

    @NotEmpty(message="내용은 필수항목입니다.")
    @Size(min=1, max=200)
    private String content;

}