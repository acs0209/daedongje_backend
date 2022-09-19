package com.mysite.sbb.meetingBoard.meetingConfigDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class MeetingDeleteInfoDto {


    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Pattern(regexp="[a-zA-Z1-9]{4,12}", message = "비밀번호는 영어 또는 숫자로 4~12자리 이내로 입력해주세요.")
    private String password;

    public MeetingDeleteInfoDto(String password) {
        this.password = password;
    }
}
