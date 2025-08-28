package org.example.zick.domain.user.persistence.dto.response;

import lombok.Getter;
import org.example.zick.domain.user.domain.User;

@Getter
public class StudentResponse {
    private String studentNumber;
    private String userName;
    private Boolean applied;
    private Boolean verified;

    public StudentResponse(User user) {
        this.studentNumber = user.getStudentNumber();
        this.userName = user.getUserName();
        this.applied = user.getApplied();
        this.verified = user.getVerified();
    }
}