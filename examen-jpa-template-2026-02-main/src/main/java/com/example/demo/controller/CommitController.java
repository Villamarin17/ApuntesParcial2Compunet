package com.example.demo.controller;

import com.example.demo.model.Commit;
import com.example.demo.repository.ICommitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/commits")
@RequiredArgsConstructor
public class CommitController {

    private final ICommitRepository commitRepository;

    @GetMapping
    public List<Commit> findAllCommits() {
        return commitRepository.findAll();
    }

    // Query 4 -> GET /commits/by-template?templateName=template-jpa-exam&keyword=fix&minLinesAdded=50
    @GetMapping("/by-template")
    public List<Commit> findByTemplateKeywordAndLines(@RequestParam("templateName") String templateName,
                                                      @RequestParam("keyword") String keyword,
                                                      @RequestParam("minLinesAdded") Integer minLinesAdded) {
        return commitRepository
                .findByRepository_ParentRepository_NameAndMessageContainingIgnoreCaseAndLinesAddedGreaterThan(
                        templateName, keyword, minLinesAdded);
    }
}
