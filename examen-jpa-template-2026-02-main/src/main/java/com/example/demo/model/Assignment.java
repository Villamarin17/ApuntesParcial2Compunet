package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assignments")
public class Assignment {

    // FIX: @Id / @GeneratedValue were on deadline; the primary key is id (Long)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // FIX: id2 renamed to description (TEXT, nullable according to the model)
    @Column(columnDefinition = "TEXT")
    private String description;

    // FIX: deadline is a NOT NULL timestamp
    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

    // FIX: many assignments belong to ONE classroom -> @ManyToOne + FK classroom_id
    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    // FIX: one assignment has MANY repositories -> @OneToMany List, mappedBy Repository.assignment
    @JsonIgnore
    @OneToMany(mappedBy = "assignment")
    private List<Repository> repositories = new ArrayList<>();
}
