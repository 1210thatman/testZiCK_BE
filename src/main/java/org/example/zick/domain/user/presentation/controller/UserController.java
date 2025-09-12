package org.example.zick.domain.user.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.user.persistence.dto.response.MyPageResponse;
import org.example.zick.domain.user.service.GetUserByUserIdService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final GetUserByUserIdService getUserByUserIdService;

    @GetMapping("/me")
    public MyPageResponse getUserByUserName(@RequestParam String loginId) {
        return getUserByUserIdService.getMyPage(loginId);
    }
}