package org.example.zick.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.user.domain.User;
import org.example.zick.domain.user.domain.repository.UserReposiotry;
import org.example.zick.domain.user.exception.UserNotFoundException;
import org.example.zick.domain.user.persistence.dto.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetStudentByStudentNameService {
    private final UserReposiotry userReposiotry;

    public UserResponse excute(String userName) {
        Optional<User> userOptional = userReposiotry.findByUserName(userName);
        return userOptional.map(UserResponse::new).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}