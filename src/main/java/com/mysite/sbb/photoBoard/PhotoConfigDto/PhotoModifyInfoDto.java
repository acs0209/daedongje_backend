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
public class PhotoModifyInfoDto {

    @NotBlank(message = "내용은 필수 항목입니다.")
    @Size(min=1, max=200, message = "내용은 최소 한 글자 이상, 200자 이하여야 합니다.")
    private String content;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min=4, max=60, message = "비밀번호는 네 자리 이상이어야 합니다.")
    private String password;

    public PhotoModifyInfoDto(String content, String password) {
        this.content = content;
        this.password = password;
    }
}
