package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    // FIX: missing @Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    // FIX: full_name was mapped on role (duplicated column full_name)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String role;

    // FIX: classrooms taught by this user (Classroom.teacher)
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Classroom> taughtClassrooms = new ArrayList<>();

    // FIX: repositories owned by this user (Repository.owner), typed as Repository
    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Repository> ownedRepositories = new ArrayList<>();

    // FIX: a List is a @OneToMany (@ManyToOne has no mappedBy)
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<PullRequest> authoredPullRequests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "reviewer")
    private List<PullRequest> reviewedPullRequests = new ArrayList<>();

    // FIX: @OneToMany mappedBy the attribute in Commit ("author"), not "commits"
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Commit> commits = new ArrayList<>();
}
