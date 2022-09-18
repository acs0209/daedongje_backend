package com.mysite.sbb.meetingBoard.meetingApi.questionApiController;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionModifyForm {

    private String content;

    private boolean success;

    public QuestionModifyForm(String content) {
        this.content = content;
        this.success = true;
    }

}
