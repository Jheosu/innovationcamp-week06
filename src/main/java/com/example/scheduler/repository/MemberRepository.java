package com.example.scheduler.repository;

import com.example.scheduler.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);
    boolean existsByNickname(String nickname);

}
