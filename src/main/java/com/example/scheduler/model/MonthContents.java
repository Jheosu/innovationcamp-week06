package com.example.scheduler.model;

import com.example.scheduler.dto.MonthContentsPostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Monthcontents")
public class MonthContents extends Timestamped {
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

    public MonthContents(String nickname, String contents) {
        this.nickname = nickname;
        this.contents = contents;
    }

    public void update(MonthContentsPostRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

    public MonthContents(MonthContentsPostRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.nickname = requestDto.getNickname();
    }
}