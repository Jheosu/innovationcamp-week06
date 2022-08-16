package com.example.scheduler.model;

import com.example.scheduler.dto.WeekContentsPostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Weekcontents")
public class WeekContents extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    // LAZY - 삭제 동작 안해서 다시 변경
    @ManyToOne
    private Member member;

    public WeekContents(String nickname, String contents) {
        this.nickname = nickname;
        this.contents = contents;
    }

    public void update(WeekContentsPostRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

    public WeekContents(WeekContentsPostRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.nickname = requestDto.getNickname();
    }
}