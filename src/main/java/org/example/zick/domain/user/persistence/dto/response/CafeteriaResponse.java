package org.example.zick.domain.user.persistence.dto.response;

import lombok.Getter;
import org.example.zick.domain.user.domain.User;

@Getter
public class CafeteriaResponse {
    private String userName;

    public CafeteriaResponse(User user) {
        this.userName = user.getUserName();
    }
}
