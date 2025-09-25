package org.example.zick.domain.student.persistence.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckCanEnterResponse {
    private final boolean canEnter;
}