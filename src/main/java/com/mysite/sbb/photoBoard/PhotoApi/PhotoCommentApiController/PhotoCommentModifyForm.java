package com.mysite.sbb.photoBoard.PhotoApi.PhotoCommentApiController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoCommentModifyForm {

    private String content;

    private boolean success;

    public PhotoCommentModifyForm(String content) {
        this.content = content;
        this.success = true; // 댓글 수정 성공 시 true 반환
    }

}
