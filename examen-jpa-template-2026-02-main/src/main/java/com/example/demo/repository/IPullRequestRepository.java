package com.example.demo.repository;

import com.example.demo.model.PullRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPullRequestRepository extends JpaRepository<PullRequest, Long> {

    /**
     * Query 1: pull requests of a classroom (by name) with a given status,
     * ordered by creation date descending.
     * Path: PullRequest -> repository -> assignment -> classroom -> name
     */
    List<PullRequest> findByRepository_Assignment_Classroom_NameAndStatusOrderByCreatedAtDesc(
            String classroomName, String status);

    /**
     * Query 3: pull requests whose reviewer has a given role, whose author has a given username,
     * and that belong to a classroom of a given semester.
     * Path: PullRequest -> reviewer -> role
     *       PullRequest -> author -> username
     *       PullRequest -> repository -> assignment -> classroom -> semester
     */
    List<PullRequest> findByReviewer_RoleAndAuthor_UsernameAndRepository_Assignment_Classroom_Semester(
            String reviewerRole, String authorUsername, String semester);
}
