package com.example.demo.repository;

import com.example.demo.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IRepositoryRepository extends JpaRepository<Repository, Long> {

    /**
     * Query 2: derived repositories (parent template not null) whose classroom teacher has a given
     * email and whose assignment deadline is after a given date.
     * Path: Repository -> parentRepository (IS NOT NULL)
     *       Repository -> assignment -> classroom -> teacher -> email
     *       Repository -> assignment -> deadline
     */
    List<Repository> findByParentRepositoryIsNotNullAndAssignment_Classroom_Teacher_EmailAndAssignment_DeadlineAfter(
            String teacherEmail, LocalDateTime deadline);
}
