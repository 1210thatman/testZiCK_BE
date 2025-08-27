package org.example.zick.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.student.domain.reposiitory.StudentHashRepository;
import org.example.zick.domain.student.exception.KeyNotFoundException;
import org.example.zick.domain.student.persistence.response.CanEnterResponse;
import org.example.zick.domain.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckCanEnterService {
    private final StudentHashRepository studentHashRepository;
    private final UserRepository userRepository;
    public CanEnterResponse execute(String key){
        Long studentId = studentHashRepository.findById(key)
                .map(studentHash -> Long.valueOf(studentHash.getStudentId()))
                .orElseThrow(() -> KeyNotFoundException.EXCEPTION);
        Boolean canEnter = userRepository.findAppliedById(studentId);

        return new CanEnterResponse(canEnter);
    }
}