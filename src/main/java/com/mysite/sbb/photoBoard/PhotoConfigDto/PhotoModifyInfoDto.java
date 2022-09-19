package com.mysite.sbb.photoBoard.PhotoConfigDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class PhotoModifyInfoDto {

    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;

    @Pattern(regexp = "[a-zA-Z1-9]{4,12}", message = "비밀번호는 영어 또는 숫자로 4~12자리 이내로 입력해주세요.")
    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;

    public PhotoModifyInfoDto(String content, String password) {
        this.content = content;
        this.password = password;
    }
}
