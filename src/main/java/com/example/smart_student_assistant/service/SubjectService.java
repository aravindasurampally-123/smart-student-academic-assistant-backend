package com.example.smart_student_assistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smart_student_assistant.entity.Subject;
import com.example.smart_student_assistant.repository.SubjectRepository;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}