package com.example.demo.repository;

import com.example.demo.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommitRepository extends JpaRepository<Commit, Long> {

    /**
     * Query 4: commits in repositories derived from a template with a given name, whose message
     * contains a keyword (case-insensitive) and whose added lines are strictly greater than a value.
     * Path: Commit -> repository -> parentRepository -> name
     */
    List<Commit> findByRepository_ParentRepository_NameAndMessageContainingIgnoreCaseAndLinesAddedGreaterThan(
            String templateName, String keyword, Integer minLinesAdded);
}
