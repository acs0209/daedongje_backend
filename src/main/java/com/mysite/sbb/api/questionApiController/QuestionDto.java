package com.mysite.sbb.api.questionApiController;

import com.mysite.sbb.api.commentApiController.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {

    private Long id;

    private String subject;

    private String content;

    private LocalDateTime createDate;

    private String username;

    private Integer view;

    private Integer voter;

    public QuestionDto(Long id, String subject, String content, LocalDateTime createDate,
                       String username, Integer view) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.username = username;
        this.view = view;
    }


}
