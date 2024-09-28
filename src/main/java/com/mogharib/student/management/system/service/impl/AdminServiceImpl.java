package com.mogharib.student.management.system.service.impl;

import com.mogharib.student.management.system.auth.RegisterRequest;
import com.mogharib.student.management.system.dto.CourseDto;
import com.mogharib.student.management.system.entity.Course;
import com.mogharib.student.management.system.entity.Student;
import com.mogharib.student.management.system.enums.UserRoleEnum;
import com.mogharib.student.management.system.repository.CourseRepository;
import com.mogharib.student.management.system.repository.StudentRepository;
import com.mogharib.student.management.system.service.AdminService;
import com.mogharib.student.management.system.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminServiceImpl extends BaseService implements AdminService {
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public void createCourse(CourseDto courseDto) {
        courseDto.setCourseCode("C-" + UUID.randomUUID().toString().substring(0, 16));
        courseRepository.save(modelMapper.map(courseDto, Course.class));
    }

    public void createAdminUser(RegisterRequest request) {
        Student student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .age(request.getAge())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .role(UserRoleEnum.ADMIN.value)
                .build();
        studentRepository.save(student);
    }
}
