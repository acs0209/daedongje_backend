package com.mysite.sbb.lostBoard.lostDto;

import com.mysite.sbb.entity.lostEntity.LostComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LostAnswerDto {

    private Long id;

    private String content;

    private String createDate;

    private String username;

    private List<LostComment> lostCommentList;


    public LostAnswerDto(Long id, String content, String createDate,
                         String username, List<LostComment> lostCommentList) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.username = username;
        this.lostCommentList = lostCommentList;
    }
}