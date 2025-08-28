package org.example.zick.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetStudentByStudentNameService {
    private final UserRepository userReposiotry;

}