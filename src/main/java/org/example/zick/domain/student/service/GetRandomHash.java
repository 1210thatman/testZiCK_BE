package org.example.zick.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.example.zick.domain.user.domain.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class GetRandomHash {
    public String makeHash (Long studeentId) throws NoSuchAlgorithmException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String rawValue = studeentId.toString() + timestamp;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(rawValue.getBytes(StandardCharsets.UTF_8));

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) hex.append(String.format("%02x", b));

        System.out.println(hex.toString());
        return hex.toString();

    }
}
