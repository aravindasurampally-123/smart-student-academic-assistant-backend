package com.example.smart_student_assistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smart_student_assistant.entity.StudyProgress;

public interface StudyProgressRepository extends JpaRepository<StudyProgress, Long> {

    List<StudyProgress> findByStudentId(Long studentId);
}