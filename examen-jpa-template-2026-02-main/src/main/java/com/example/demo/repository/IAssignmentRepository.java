package com.example.demo.repository;

import com.example.demo.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAssignmentRepository extends JpaRepository<Assignment, Long> {

    /**
     * Query 5: assignments of a teacher (by username) that have repositories with pull requests
     * reviewed by a given reviewer (by username) and with a given PR status.
     * Path: Assignment -> classroom -> teacher -> username
     *       Assignment -> repositories -> pullRequests -> reviewer -> username
     *       Assignment -> repositories -> pullRequests -> status
     * Distinct: an assignment can match through several repositories / pull requests.
     */
    List<Assignment> findDistinctByClassroom_Teacher_UsernameAndRepositories_PullRequests_Reviewer_UsernameAndRepositories_PullRequests_Status(
            String teacherUsername, String reviewerUsername, String status);
}
