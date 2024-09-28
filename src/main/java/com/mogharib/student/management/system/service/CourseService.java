package com.mogharib.student.management.system.service;

import com.mogharib.student.management.system.dto.CourseDto;
import com.mogharib.student.management.system.dto.PaginationResponse;

import java.io.ByteArrayOutputStream;

public interface CourseService {
    PaginationResponse<CourseDto> viewCourses(Integer pageNo, Integer pageSize);

    void registerToCourse(Long studentId, Long courseId);

    void cancelCourseRegistration(Long studentId, Long courseId);
    ByteArrayOutputStream getCoursesSchedulePdf(Long studentId);
}
