package org.example.zick.domain.student.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "student_hash")
public class StudentHash {
    @Id
    private String hash;

    private String studentId;

    @TimeToLive
    private Long ttl;
}
