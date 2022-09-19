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
public class PhotoDeleteInfoDto {


    @Pattern(regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$", message = "닉네임은 2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성해주세요.")
    @NotBlank(message = "닉네임은 필수 항목입니다.")
    private String password;

    public PhotoDeleteInfoDto(String password) {
        this.password = password;
    }
}
