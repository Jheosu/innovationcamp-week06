package com.example.scheduler.repository;



import com.example.scheduler.model.MonthContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthContentsRepository extends JpaRepository<MonthContents, Long> {
    List<MonthContents> findAllByOrderByModifiedAtDesc();
}