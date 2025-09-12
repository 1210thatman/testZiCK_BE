package org.example.zick.domain.user.persistence.dto.response;

import lombok.Getter;
import org.example.zick.domain.user.domain.User;

@Getter
public class StudentMyPageResponse implements MyPageResponse {
    private final String studentNumber;
    private final String userName;
    private final String userId;
    private final Boolean applied;
    private final Boolean verified;

    public StudentMyPageResponse(User user) {
        this.studentNumber = user.getStudentNumber();
        this.userName = user.getUserName();
        this.userId = user.getLoginId();
        this.applied = user.getApplied();
        this.verified = user.getVerified();
    }
}