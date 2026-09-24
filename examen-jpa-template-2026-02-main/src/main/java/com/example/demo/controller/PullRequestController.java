package com.example.demo.controller;

import com.example.demo.model.PullRequest;
import com.example.demo.repository.IPullRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pull-requests")
@RequiredArgsConstructor
public class PullRequestController {

    private final IPullRequestRepository pullRequestRepository;

    @GetMapping
    public List<PullRequest> findAllPullRequests() {
        return pullRequestRepository.findAll();
    }

    // Query 1 -> GET /pull-requests/by-classroom?classroomName=Computacion en Internet II - Grupo 1&status=OPEN
    @GetMapping("/by-classroom")
    public List<PullRequest> findByClassroomAndStatus(@RequestParam("classroomName") String classroomName,
                                                      @RequestParam("status") String status) {
        return pullRequestRepository
                .findByRepository_Assignment_Classroom_NameAndStatusOrderByCreatedAtDesc(classroomName, status);
    }

    // Query 3 -> GET /pull-requests/by-reviewer-role?reviewerRole=TA&authorUsername=cjimenez&semester=2026-02
    @GetMapping("/by-reviewer-role")
    public List<PullRequest> findByReviewerRoleAuthorAndSemester(@RequestParam("reviewerRole") String reviewerRole,
                                                                 @RequestParam("authorUsername") String authorUsername,
                                                                 @RequestParam("semester") String semester) {
        return pullRequestRepository
                .findByReviewer_RoleAndAuthor_UsernameAndRepository_Assignment_Classroom_Semester(
                        reviewerRole, authorUsername, semester);
    }
}
