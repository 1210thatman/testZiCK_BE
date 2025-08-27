package org.example.zick.domain.student.prsentation.controller;


import lombok.RequiredArgsConstructor;
import org.example.zick.domain.student.persistence.response.CanEnterResponse;
import org.example.zick.domain.student.service.CheckCanEnterService;
import org.example.zick.domain.student.service.GetRandomHash;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final GetRandomHash getRandomHash;
    private final CheckCanEnterService checkCanEnterService;

    @GetMapping("/qr")
    public String getRandomHash(@RequestParam Long studentId) throws NoSuchAlgorithmException {
        return getRandomHash.makeHash(studentId);
    }

    @PostMapping("/attendances")
    public CanEnterResponse checkCanEnterService(@RequestBody String key){
        return checkCanEnterService.execute(key);
    }
}
