package com.mysite.sbb.meetingBoard.meetingApi.commentApiController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentModifyForm {

    private String content;
    private boolean success;

    public CommentModifyForm(String content) {

        this.content = content;
        this.success = true;
    }

}
