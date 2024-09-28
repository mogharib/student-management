package com.mogharib.student.management.system.service.impl;

import com.mogharib.student.management.system.entity.Student;
import com.mogharib.student.management.system.exception.ResourceNotFoundException;
import com.mogharib.student.management.system.repository.StudentRepository;
import com.mogharib.student.management.system.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

}
