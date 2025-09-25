package org.example.zick.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GetRandomHash {

    private final RedisTemplate<String, String> redisTemplate;

    public String makeHash (Long studentId) throws NoSuchAlgorithmException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String rawValue = studentId.toString() + timestamp;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(rawValue.getBytes(StandardCharsets.UTF_8));

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) hex.append(String.format("%02x", b));

        saveHashToRedis(hex.toString(), studentId);
        return hex.toString();
    }

    private void saveHashToRedis(String hash, Long studentId) {
        //나중에 300L(5분) -> 30L(30초)로 변경할 것
        redisTemplate.opsForValue().set(hash, String.valueOf(studentId), 300L, TimeUnit.SECONDS);
    }
}