package com.mogharib.student.management.system.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Schema(name = "login",
        description = "student login")
public class LoginRequest {
    @Email
    @Schema(description = "Student's email", example = "9mohamedabdelhamied8@gmail.com")
    private String email;

    @Schema(description = "Student's password", example = "mohamed123")
    private String password;
}
