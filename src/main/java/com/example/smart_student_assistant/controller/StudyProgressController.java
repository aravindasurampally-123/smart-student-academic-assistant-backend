package com.example.smart_student_assistant.controller;

import com.example.smart_student_assistant.entity.StudyProgress;
import com.example.smart_student_assistant.service.StudyProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-progress")
public class StudyProgressController {

    private final StudyProgressService studyProgressService;

    public StudyProgressController(StudyProgressService studyProgressService) {
        this.studyProgressService = studyProgressService;
    }

    @PostMapping
    public StudyProgress createProgress(@RequestBody StudyProgress progress) {
        return studyProgressService.createProgress(progress);
    }

    @GetMapping
    public List<StudyProgress> getAllProgress() {
        return studyProgressService.getAllProgress();
    }

    @GetMapping("/{id}")
    public StudyProgress getProgressById(@PathVariable Long id) {
        return studyProgressService.getProgressById(id);
    }

    @PutMapping("/{id}")
    public StudyProgress updateProgress(
            @PathVariable Long id,
            @RequestBody StudyProgress progress) {

        return studyProgressService.updateProgress(id, progress);
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable Long id) {
        studyProgressService.deleteProgress(id);
    }
}