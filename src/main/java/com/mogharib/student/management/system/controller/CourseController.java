package com.mogharib.student.management.system.controller;

import com.mogharib.student.management.system.dto.CourseDto;
import com.mogharib.student.management.system.dto.ErrorResponseDto;
import com.mogharib.student.management.system.dto.PaginationResponse;
import com.mogharib.student.management.system.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@Tag(name = "Course")
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CourseController {
    private final CourseService courseService;

    @Operation(
            summary = "List all Courses with pagination",
            description = "REST API to list all Courses with pagination order by create date desc"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            )
    }
    )
   @GetMapping("/courses")
    public ResponseEntity<PaginationResponse<CourseDto>> getCourses(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        PaginationResponse<CourseDto> courses = courseService.viewCourses(pageNo, pageSize);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courses);
    }


    @Operation(
            summary = "Register a student to a course",
            description = "REST API to register a student to a course by student ID and course ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Student successfully registered to the course"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student or Course not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Course already exists in student's course list",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<String> registerToCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        courseService.registerToCourse(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered successfully");
    }



    @Operation(
            summary = "Cancel a student's course registration",
            description = "REST API to cancel a student's registration to a course by student ID and course ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Course registration successfully canceled"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student or Course not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Course not found in student's course list",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("students/{studentId}/courses/{courseId}")
    public ResponseEntity<String> cancelCourseRegistration(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        courseService.cancelCourseRegistration(studentId, courseId);
        return ResponseEntity.status(HttpStatus.OK).body("canceled successfully");
    }


    @Operation(
            summary = "Get student's course schedule as PDF",
            description = "REST API to retrieve the course schedule for a student as a PDF document"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Course schedule successfully generated",
                    content = @Content(
                            mediaType = "application/pdf"
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/students/{studentId}/course-schedule")
    public ResponseEntity<byte[]> getCourseScheduleAsPdf(@PathVariable Long studentId) {
        ByteArrayOutputStream pdfOutputStream = courseService.getCoursesSchedulePdf(studentId);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=course_schedule.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfOutputStream.toByteArray());
    }


}
