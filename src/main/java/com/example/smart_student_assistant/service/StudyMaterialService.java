package com.example.smart_student_assistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smart_student_assistant.entity.StudyMaterial;
import com.example.smart_student_assistant.repository.StudyMaterialRepository;

@Service
public class StudyMaterialService {

    private final StudyMaterialRepository studyMaterialRepository;

    public StudyMaterialService(StudyMaterialRepository studyMaterialRepository) {
        this.studyMaterialRepository = studyMaterialRepository;
    }

    public StudyMaterial addStudyMaterial(StudyMaterial studyMaterial) {
        return studyMaterialRepository.save(studyMaterial);
    }

    public List<StudyMaterial> getAllStudyMaterials() {
        return studyMaterialRepository.findAll();
    }

    public List<StudyMaterial> getStudyMaterialsBySubject(Long subjectId) {
        return studyMaterialRepository.findBySubjectId(subjectId);
    }

    public StudyMaterial getStudyMaterialById(Long id) {
        return studyMaterialRepository.findById(id).orElse(null);
    }

    public void deleteStudyMaterial(Long id) {
        studyMaterialRepository.deleteById(id);
    }
}