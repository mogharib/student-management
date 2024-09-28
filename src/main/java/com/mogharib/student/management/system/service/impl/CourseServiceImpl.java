package com.mogharib.student.management.system.service.impl;

import com.mogharib.student.management.system.dto.CourseDto;
import com.mogharib.student.management.system.dto.PaginationResponse;
import com.mogharib.student.management.system.entity.Course;
import com.mogharib.student.management.system.entity.Student;
import com.mogharib.student.management.system.exception.CourseAlreadyExistsException;
import com.mogharib.student.management.system.exception.CourseNotRegisteredException;
import com.mogharib.student.management.system.exception.ResourceNotFoundException;
import com.mogharib.student.management.system.repository.CourseRepository;
import com.mogharib.student.management.system.repository.StudentRepository;
import com.mogharib.student.management.system.service.BaseService;
import com.mogharib.student.management.system.service.CourseSchedulePdfService;
import com.mogharib.student.management.system.service.CourseService;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;

@AllArgsConstructor
@Service
public class CourseServiceImpl extends BaseService implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final CourseSchedulePdfService courseSchedulePdfService;

    @Override
    @Cacheable(value = "courses", key = "#pageNo + '-' + #pageSize")
    public PaginationResponse<CourseDto> viewCourses(Integer pageNo, Integer pageSize) {
        Page<Course> pages = courseRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(pageNo, pageSize));

        List<CourseDto> courses = modelMapper.map(pages.getContent(), new TypeToken<List<CourseDto>>() {
        }.getType());

        return PaginationResponse.<CourseDto>builder()
                .data(courses)
                .totalItems(pages.getTotalElements())
                .currentPage(pages.getNumber())
                .totalPages(pages.getTotalPages())
                .build();
    }

    @Override
    @Transactional
    public void registerToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (student.getCourses().contains(course)) {
            throw new CourseAlreadyExistsException("Course already exists in your course list");
        }

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelCourseRegistration(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        List<Long> studentCoursesIds = student.getCourses().stream().map(Course::getId).toList();
        if (!studentCoursesIds.contains(courseId)) {
            throw new CourseNotRegisteredException("Course not found in your course list");
        }
        student.getCourses().remove(course);
        studentRepository.save(student);
    }

    @Override
    public ByteArrayOutputStream getCoursesSchedulePdf(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found");
        }
        List<Course> courses = courseRepository.findCoursesByStudentId(studentId);
        return courseSchedulePdfService.generateCourseSchedulePdf(courses);
    }
}
