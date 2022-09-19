package com.mysite.sbb.meetingBoard.meetingConfigDto.meetingController.answerController;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AnswerForm {

    @NotBlank(message = "사용자 이름은 필수 항목입니다.")
    @Pattern(regexp="^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$", message = "닉네임은 2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성해주세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Pattern(regexp="[a-zA-Z1-9]{4,12}", message = "비밀번호는 영어 또는 숫자로 4~12자리 이내로 입력해주세요.")
    private String password;

    @NotBlank(message = "내용은 필수 항목입니다.")
    @Size(min=1, max=200)
    private String content;
}
