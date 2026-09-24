package com.example.demo.controller;

import com.example.demo.model.Assignment;
import com.example.demo.repository.IAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final IAssignmentRepository assignmentRepository;

    @GetMapping
    public List<Assignment> findAllAssignments() {
        return assignmentRepository.findAll();
    }

    // Query 5 -> GET /assignments/by-teacher-reviewer?teacherUsername=krodriguez&reviewerUsername=jvalencia&status=MERGED
    @GetMapping("/by-teacher-reviewer")
    public List<Assignment> findByTeacherReviewerAndStatus(@RequestParam("teacherUsername") String teacherUsername,
                                                           @RequestParam("reviewerUsername") String reviewerUsername,
                                                           @RequestParam("status") String status) {
        return assignmentRepository
                .findDistinctByClassroom_Teacher_UsernameAndRepositories_PullRequests_Reviewer_UsernameAndRepositories_PullRequests_Status(
                        teacherUsername, reviewerUsername, status);
    }
}
