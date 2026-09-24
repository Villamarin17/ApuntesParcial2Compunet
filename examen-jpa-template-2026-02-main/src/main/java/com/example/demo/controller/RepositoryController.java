package com.example.demo.controller;

import com.example.demo.model.Repository;
import com.example.demo.repository.IRepositoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/repositories")
@RequiredArgsConstructor
public class RepositoryController {

    private final IRepositoryRepository repositoryRepository;

    @GetMapping
    public List<Repository> findAllRepositories() {
        return repositoryRepository.findAll();
    }

    // Query 2 -> GET /repositories/derived?teacherEmail=krodriguez@icesi.edu.co&deadlineAfter=2026-03-15 00:00:00
    @GetMapping("/derived")
    public List<Repository> findDerivedByTeacherEmailAndDeadline(
            @RequestParam("teacherEmail") String teacherEmail,
            @RequestParam("deadlineAfter") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime deadlineAfter) {
        return repositoryRepository
                .findByParentRepositoryIsNotNullAndAssignment_Classroom_Teacher_EmailAndAssignment_DeadlineAfter(
                        teacherEmail, deadlineAfter);
    }
}
