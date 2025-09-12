package org.example.zick.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.user.domain.Role;
import org.example.zick.domain.user.domain.User;
import org.example.zick.domain.user.domain.repository.UserRepository;
import org.example.zick.domain.user.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;

    public Role getRoleById(String id){
        return userRepository.findByLoginId(id)
                .map(User::getRole)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
