package com.mysite.sbb.photoBoard.PhotoConfigDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PhotoDeleteInfoDto {


    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min=4, max=60, message = "비밀번호는 네 자리 이상이어야 합니다.")
    private String password;

    public PhotoDeleteInfoDto(String password) {
        this.password = password;
    }
}
