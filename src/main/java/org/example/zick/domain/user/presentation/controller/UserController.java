package org.example.zick.domain.user.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.user.persistence.dto.response.StudentResponse;
import org.example.zick.domain.user.service.GetStudentByStudentNameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final GetStudentByStudentNameService getStudentByStudentNameService;

    @GetMapping("/me")
    public StudentResponse getUserByUserName(@RequestParam String userName) {
        return getStudentByStudentNameService.execute(userName);
    }
}