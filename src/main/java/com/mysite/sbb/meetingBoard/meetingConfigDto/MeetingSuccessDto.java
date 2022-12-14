package com.mysite.sbb.meetingBoard.meetingConfigDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MeetingSuccessDto {

    private Boolean success;

    public MeetingSuccessDto(Boolean success) {
        this.success = success;
    }

}
