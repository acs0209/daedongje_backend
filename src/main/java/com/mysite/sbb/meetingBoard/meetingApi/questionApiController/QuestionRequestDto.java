package com.mysite.sbb.meetingBoard.meetingApi.questionApiController;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class QuestionRequestDto {

    @NotBlank(message = "비밀번호는 필수항목입니다.")
    @Pattern(regexp="[a-zA-Z1-9]{4,12}", message = "비밀번호는 영어 또는 숫자로 4~12자리 이내로 입력해주세요.")
    private String password;

    @NotBlank(message="내용은 필수항목입니다.")
    @Size(min=1, max=200)
    private String content;

}
