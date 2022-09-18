package com.mysite.sbb.meetingBoard.meetingConfigDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class MeetingDeleteInfoDto {


    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    @Size(min=4, max=60)
    private String password;

    public MeetingDeleteInfoDto(String password) {
        this.password = password;
    }
}
