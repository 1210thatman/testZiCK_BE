package org.example.zick.domain.user.persistence.dto.response;

import lombok.Getter;
import org.example.zick.domain.user.domain.User;

@Getter
public class CafeteriaMyPageResponse implements MyPageResponse {
    private final String userName;
    private final String userId;

    public CafeteriaMyPageResponse(User user) {
        this.userName = user.getUserName();
        this.userId = user.getLoginId();
    }
}
