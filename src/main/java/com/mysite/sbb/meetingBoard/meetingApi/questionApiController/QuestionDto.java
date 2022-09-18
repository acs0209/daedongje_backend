package com.mysite.sbb.meetingBoard.meetingApi.questionApiController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {

    private Long id;

    private String content;

    private String createDate;

    private String username;

    private Integer view;

    public QuestionDto(Long id, String content, String createDate,
                       String username, Integer view) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.username = username;
        this.view = view;
    }
}
