package com.mogharib.student.management.system.service;

import com.mogharib.student.management.system.auth.RegisterRequest;
import com.mogharib.student.management.system.dto.CourseDto;

public interface AdminService {
    void createCourse(CourseDto courseDto);
    void createAdminUser(RegisterRequest registerRequest);
}
