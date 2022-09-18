package com.mysite.sbb.meetingBoard.meetingApi.questionApiController;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionModifyForm {

    private String subject;

    private String content;

    private boolean success;

    public QuestionModifyForm(String subject, String content) {
        this.subject = subject;
        this.content = content;
        this.success = true;
    }

}
