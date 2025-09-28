package org.example.zick.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.user.domain.User;
import org.example.zick.domain.user.domain.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component //@Service와 기능상 동일하나 용도 구분을 위해서 @Component 사용
@RequiredArgsConstructor
public class MealResetScheduler {

    private final UserRepository userRepository;

    //아침 식사 종료 후 초기화
    @Scheduled(cron = "0 0 9 * * *")
    @Transactional
    public void resetBreakfast(){
        resetAllverifications();
    }

    private void resetAllverifications(){
        List<User> users = userRepository.findAll();
        for(User user : users){
            user.updateVerified(false);
        }
    }
}
