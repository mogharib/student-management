package com.mogharib.student.management.system.service;

import com.mogharib.student.management.system.entity.Student;

public interface StudentService {
    Student findByEmail(String email);
    void createStudent(Student student);

}
