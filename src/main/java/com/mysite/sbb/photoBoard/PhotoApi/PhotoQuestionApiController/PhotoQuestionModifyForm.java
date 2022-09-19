package com.mysite.sbb.photoBoard.PhotoApi.PhotoQuestionApiController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoQuestionModifyForm {

    private String subject;
    private String content;
    private String filename;
    private String filepath;

    private boolean success; 


    public PhotoQuestionModifyForm(String subject, String content, String filename, String filepath) {
        this.subject = subject;
        this.content = content;
        this.filename = filename;
        this.filepath = filepath;
        this.success = true; // 게시글 수정 시 true 반환
    }
}