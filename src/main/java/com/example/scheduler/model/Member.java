package com.example.scheduler.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member  {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    


    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String nickname,String username, String password, Authority authority) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
}