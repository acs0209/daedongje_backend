package com.mysite.sbb.meetingBoard.meetingConfigDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class MeetingModifyInfoDto {

    @NotEmpty(message = "내용은 필수 항목입니다.")
    @Size(min=1, max=200)
    private String content;

    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    @Size(min=4, max=60)
    private String password;

    public MeetingModifyInfoDto(String content, String password) {
        this.content = content;
        this.password = password;
    }
}
