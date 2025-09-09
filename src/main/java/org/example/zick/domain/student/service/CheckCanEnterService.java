package org.example.zick.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.student.exception.KeyNotFoundException;
import org.example.zick.domain.student.persistence.response.CanEnterResponse;
import org.example.zick.domain.user.domain.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckCanEnterService {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;
    public CanEnterResponse execute(String key){
        String studentIdStr = redisTemplate.opsForValue().get(key);
        if(studentIdStr == null){
            throw KeyNotFoundException.EXCEPTION;
        }
        Long studentId = Long.valueOf(studentIdStr);

        Boolean isEnter = userRepository.findAppliedById(studentId);

        return new CanEnterResponse(isEnter);
    }
}

