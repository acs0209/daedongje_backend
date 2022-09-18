package com.mysite.sbb.meetingBoard.meetingApi.questionApiController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionApiForm {

    private String content;
    private String username;
    private String createDate;
    private boolean success;

    public QuestionApiForm(String content, String username, String createDate) {

        this.content = content;
        this.username = username;
        this.createDate = createDate;
        this.success = true;
    }

}