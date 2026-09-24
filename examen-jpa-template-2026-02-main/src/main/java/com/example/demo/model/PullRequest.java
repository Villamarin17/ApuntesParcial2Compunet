package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pull_requests")
public class PullRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String status;

    @Column(name = "pr_number", nullable = false)
    private Integer prNumber;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // FIX: many PRs belong to ONE repository -> @ManyToOne Repository (not a List)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repository_id", nullable = false)
    private Repository repository;

    // FIX: author_id was missing
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    // FIX: reviewer is @ManyToOne User named "reviewer" (matches User.reviewedPullRequests); nullable in the model
    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;
}
