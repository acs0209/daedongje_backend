package com.mysite.sbb.meetingBoard.meetingConfigDto.meetingController.answerController;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AnswerForm {

    @NotEmpty(message = "내용은 필수 항목입니다.")
    @Size(min=1, max=200)
    private String content;

    @NotEmpty(message = "사용자 이름은 필수 항목입니다.")
    @Size(min=4, max=15)
    private String username;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    @Size(min=4, max=60)
    private String password;


}
