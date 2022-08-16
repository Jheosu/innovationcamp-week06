package com.example.scheduler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeekContentsPostRequestDto {
    private String title;
    private String contents;
    private String nickname;
}