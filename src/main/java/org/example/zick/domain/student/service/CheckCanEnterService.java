package org.example.zick.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.student.domain.AttendanceLog;
import org.example.zick.domain.student.domain.MealType;
import org.example.zick.domain.student.domain.repository.AttendanceLogRepository;
import org.example.zick.domain.student.exception.KeyNotFoundException;
import org.example.zick.domain.student.persistence.response.CheckCanEnterResponse;
import org.example.zick.domain.user.domain.User;
import org.example.zick.domain.user.domain.repository.UserRepository;
import org.example.zick.domain.user.exception.UserNotFoundException;
import org.example.zick.global.util.MealTypeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckCanEnterService {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;
    private final AttendanceLogRepository attendanceLogRepository;

    public CheckCanEnterResponse execute(String key){
        String studentIdStr = redisTemplate.opsForValue().get(key);
        if(studentIdStr == null){
            throw KeyNotFoundException.EXCEPTION;
        }
        Long studentId = Long.valueOf(studentIdStr);

        Boolean applied = userRepository.findById(studentId).orElseThrow(() -> UserNotFoundException.EXCEPTION).getApplied();
        boolean status = applied != null && applied; //신청 시 true, 아니면 false

        MealType mealType = MealTypeUtil.getCurrentMealType();

        User user = userRepository.findById(studentId)
                .orElseThrow(() ->  UserNotFoundException.EXCEPTION);

        AttendanceLog log = new AttendanceLog(user, mealType, status);
        attendanceLogRepository.save(log);

        if(status){
            user.updateVerified(true);
        }

        return new CheckCanEnterResponse(status);
    }
}

