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
@Table(name = "commits")
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commit_hash", nullable = false, unique = true)
    private String commitHash;

    @Column(nullable = false)
    private String message;

    // FIX: missing attributes from the model
    @Column(name = "lines_added", nullable = false)
    private Integer linesAdded;

    @Column(name = "lines_deleted", nullable = false)
    private Integer linesDeleted;

    @Column(name = "commit_date", nullable = false)
    private LocalDateTime commitDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repository_id", nullable = false)
    private Repository repository;

    // FIX: many commits are made by ONE author -> @ManyToOne User (not List<User>)
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
}
