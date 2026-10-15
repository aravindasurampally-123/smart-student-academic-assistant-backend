package com.example.smart_student_assistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smart_student_assistant.entity.StudyProgress;
import com.example.smart_student_assistant.repository.StudyProgressRepository;

@Service
public class StudyProgressService {

    private final StudyProgressRepository studyProgressRepository;

    public StudyProgressService(StudyProgressRepository studyProgressRepository) {
        this.studyProgressRepository = studyProgressRepository;
    }

    public StudyProgress createProgress(StudyProgress progress) {
        return studyProgressRepository.save(progress);
    }

    public List<StudyProgress> getAllProgress() {
        return studyProgressRepository.findAll();
    }

    public StudyProgress getProgressById(Long id) {
        return studyProgressRepository.findById(id).orElse(null);
    }

    public StudyProgress updateProgress(Long id, StudyProgress progressDetails) {

        StudyProgress progress = studyProgressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study progress not found"));

        progress.setStudentId(progressDetails.getStudentId());
        progress.setSubjectId(progressDetails.getSubjectId());
        progress.setCompletedTopics(progressDetails.getCompletedTopics());
        progress.setTotalTopics(progressDetails.getTotalTopics());
        progress.setProgressPercentage(progressDetails.getProgressPercentage());

        return studyProgressRepository.save(progress);
    }

    public void deleteProgress(Long id) {
        studyProgressRepository.deleteById(id);
    }
}