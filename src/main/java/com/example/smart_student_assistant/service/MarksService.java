package com.example.smart_student_assistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smart_student_assistant.entity.Marks;
import com.example.smart_student_assistant.repository.MarksRepository;

@Service
public class MarksService {

    private final MarksRepository marksRepository;

    public MarksService(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    public Marks addMarks(Marks marks) {
        return marksRepository.save(marks);
    }

    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }

    public Marks getMarksById(Long id) {
        return marksRepository.findById(id).orElse(null);
    }

    public void deleteMarks(Long id) {
        marksRepository.deleteById(id);
    }
}