package com.mysite.sbb.lostBoard.lostDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LostPostDto {

    private Long id;

    private String title;

    private String content;

    private String createDate;

    private String username;

    private Boolean isLost;

    private String filename;

    private String filepath;

    public LostPostDto(Long id, String title, String content, String createDate,
                       String username, Boolean isLost, String filename, String filepath) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.username = username;
        this.isLost = isLost;
        this.filename = filename;
        this.filepath = filepath;

    }


}