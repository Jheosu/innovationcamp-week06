package com.example.scheduler.model;

import com.example.scheduler.dto.DayContentsPostRequestDto;
import com.example.scheduler.dto.MonthContentsPostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Monthcontents")
public class MonthContents extends  Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable =false)
    private String username;

    @Column(nullable =false)
    private String contents;

    // LAZY - 삭제 동작 안해서 다시 변경
    @ManyToOne
    private Member member;

    public MonthContents(String username, String contents){
        this.username = username;
        this.contents = contents;
    }

    public void update(DayContentsPostRequestDto requestDto){
        this.contents = requestDto.getContents();
    }

    public MonthContents(MonthContentsPostRequestDto requestDto, UserDetails userDetails){
        this.contents = requestDto.getContents();
        this.username = userDetails.getUsername();
    }
}