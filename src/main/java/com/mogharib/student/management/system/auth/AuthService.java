package com.mogharib.student.management.system.auth;

import com.mogharib.student.management.system.entity.Student;
import com.mogharib.student.management.system.enums.UserRoleEnum;
import com.mogharib.student.management.system.exception.InvalidCredentialsException;
import com.mogharib.student.management.system.exception.PasswordMismatchException;
import com.mogharib.student.management.system.security.JwtTokenUtil;
import com.mogharib.student.management.system.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtUtil;

    public void register(RegisterRequest request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());

        // validate password
        validatePassword(request);

        // encode password
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setAge(request.getAge());
        student.setAddress(request.getAddress());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setRole(UserRoleEnum.USER.value);
        studentService.createStudent(student);
    }

    public AuthResponse login(LoginRequest request) {
        Student student = studentService.findByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(student.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(student.getEmail());
        return new AuthResponse(token, refreshToken);
    }

    private void validatePassword(RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords do not match");
        }
    }
}
