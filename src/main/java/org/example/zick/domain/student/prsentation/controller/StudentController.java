package org.example.zick.domain.student.prsentation.controller;


import lombok.RequiredArgsConstructor;
import org.example.zick.domain.student.service.GetRandomHash;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final GetRandomHash getRandomHash;

    @GetMapping("/qr")
    public String getRandomHash(@RequestParam Long studentId) throws NoSuchAlgorithmException {
        return getRandomHash.makeHash(studentId);
    }
}
