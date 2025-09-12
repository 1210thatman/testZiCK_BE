package org.example.zick.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.user.domain.Role;
import org.example.zick.domain.user.domain.User;
import org.example.zick.domain.user.domain.repository.UserRepository;
import org.example.zick.domain.user.exception.InternalServerError;
import org.example.zick.domain.user.exception.UserNotFoundException;
import org.example.zick.domain.user.facade.UserFacade;
import org.example.zick.domain.user.persistence.dto.response.CafeteriaMyPageResponse;
import org.example.zick.domain.user.persistence.dto.response.MyPageResponse;
import org.example.zick.domain.user.persistence.dto.response.StudentMyPageResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByUserIdService {
    private final UserFacade userFacade;
    private final UserRepository userRepository;

    public MyPageResponse getMyPage(String loginId){
        Role role = userFacade.getRoleById(loginId);

        switch (role) {
            case STUDENT:
                return getStudentMyPage(loginId);
            case CAFETERIA:
                return getCafeteriaMyPage(loginId);
            default:
                throw InternalServerError.EXCEPTION;
        }
    }

    private MyPageResponse getStudentMyPage(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return new StudentMyPageResponse(user);
    }

    private MyPageResponse getCafeteriaMyPage(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        return new CafeteriaMyPageResponse(user);
    }
}