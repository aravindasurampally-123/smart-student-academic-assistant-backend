package com.example.smart_student_assistant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_student_assistant.entity.StudyMaterial;
import com.example.smart_student_assistant.service.StudyMaterialService;

@RestController
@RequestMapping("/api/study-materials")
public class StudyMaterialController {

    private final StudyMaterialService studyMaterialService;

    public StudyMaterialController(StudyMaterialService studyMaterialService) {
        this.studyMaterialService = studyMaterialService;
    }

    @PostMapping
    public StudyMaterial addStudyMaterial(@RequestBody StudyMaterial studyMaterial) {
        return studyMaterialService.addStudyMaterial(studyMaterial);
    }

    @GetMapping
    public List<StudyMaterial> getAllStudyMaterials() {
        return studyMaterialService.getAllStudyMaterials();
    }

    @GetMapping("/subject/{subjectId}")
    public List<StudyMaterial> getStudyMaterialsBySubject(
            @PathVariable Long subjectId) {
        return studyMaterialService.getStudyMaterialsBySubject(subjectId);
    }

    @GetMapping("/{id}")
    public StudyMaterial getStudyMaterialById(@PathVariable Long id) {
        return studyMaterialService.getStudyMaterialById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudyMaterial(@PathVariable Long id) {
        studyMaterialService.deleteStudyMaterial(id);
    }
}