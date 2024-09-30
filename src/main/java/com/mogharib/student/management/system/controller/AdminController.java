package com.mogharib.student.management.system.controller;

import com.mogharib.student.management.system.auth.RegisterRequest;
import com.mogharib.student.management.system.dto.CourseDto;
import com.mogharib.student.management.system.dto.ErrorResponseDto;
import com.mogharib.student.management.system.repository.StudentRepository;
import com.mogharib.student.management.system.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Admin")
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;
    private final StudentRepository studentRepository;

    @Operation(
            summary = "Create new Course",
            description = "REST API to create new Course"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create-course")
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto courseDto) {
        adminService.createCourse(courseDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseDto);
    }

    @Operation(
            summary = "Create new ADMIN USER",
            description = "REST API to create new Admin User"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )

    @PostMapping("/create-admin-user")
    public ResponseEntity<RegisterRequest> create(@Valid @RequestBody RegisterRequest request) {
        adminService.createAdminUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(request);
    }
}
