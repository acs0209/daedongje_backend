package com.mysite.sbb.lostBoard.lostForm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
public class LostPostCreateForm {

    @Pattern(regexp="^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$", message = "닉네임은 2자 이상 16자 이하, 영어 또는 숫자 또는 한글(모음, 자음 제외)로 구성해주세요.")
    @NotBlank(message = "닉네임은 필수 항목입니다.")
    private String username;

    @Pattern(regexp="[a-zA-Z1-9]{4,12}", message = "비밀번호는 영어 또는 숫자로 4~12자리 이내로 입력해주세요.")
    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;

    @NotBlank(message = "제목은 필수 항목입니다.")
    @Size(min=1, max=20, message = "제목은 최소 한 글자 이상, 50글자 이하여야 합니다.")
    private String subject;

    @NotBlank(message = "내용은 필수 항목입니다.")
    @Size(min=1, max=200, message = "내용은 최소 한 글자 이상, 200자 이하여야 합니다.")
    private String content;

    @NotNull
    @Column(columnDefinition = "BIT")
    private Boolean isLost;   // 분실, 발견

    private String filename;

    private String filepath;

}