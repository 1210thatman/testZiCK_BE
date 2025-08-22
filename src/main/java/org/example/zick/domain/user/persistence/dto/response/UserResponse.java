package org.example.zick.domain.user.persistence.dto.response;

import lombok.Getter;
import org.example.zick.domain.user.domain.User;

@Getter
public class UserResponse {
    private String studentNumber;
    private String userName;
    private Boolean applied;

    public UserResponse(User user) {
        this.studentNumber = user.getStudentNumber();
        this.userName = user.getUserName();
        this.applied = user.getApplied();
    }
}