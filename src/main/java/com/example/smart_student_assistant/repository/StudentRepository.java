package com.example.smart_student_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smart_student_assistant.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
}