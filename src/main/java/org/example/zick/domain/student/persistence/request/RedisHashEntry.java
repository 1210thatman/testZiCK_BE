package org.example.zick.domain.student.persistence.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RedisHashEntry {
    private String key;
    private String value;
    private Long ttl;
}
