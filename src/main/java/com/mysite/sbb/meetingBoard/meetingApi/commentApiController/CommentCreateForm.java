package com.mysite.sbb.meetingBoard.meetingApi.commentApiController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentCreateForm {

    private String username;
    private String content;
    private String createDate;
    private boolean success;

    public CommentCreateForm(String username, String content, String createDate) {
        this.username = username;
        this.content = content;
        this.createDate = createDate;
        this.success = true;
    }

}
