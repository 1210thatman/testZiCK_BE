package org.example.zick.domain.student.prsentation.controller;


import lombok.RequiredArgsConstructor;
import org.example.zick.domain.student.persistence.request.CheckCanEnterRequest;
import org.example.zick.domain.student.persistence.response.CheckCanEnterResponse;
import org.example.zick.domain.student.persistence.response.ExcelResponse;
import org.example.zick.domain.student.service.AttendanceExcelService;
import org.example.zick.domain.student.service.CheckCanEnterService;
import org.example.zick.domain.student.service.GetRandomHash;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final GetRandomHash getRandomHash;
    private final CheckCanEnterService checkCanEnterService;
    private final AttendanceExcelService attendacneExcelService;

    @GetMapping("/qr")
    public String getRandomHash(@RequestParam Long studentId) throws NoSuchAlgorithmException {
        return getRandomHash.makeHash(studentId);
    }

    @PostMapping("/attendances")
    public CheckCanEnterResponse checkCanEnterService(@RequestBody CheckCanEnterRequest checkCanEnterRequest){
        return checkCanEnterService.execute(checkCanEnterRequest.getKey());
    }

    @GetMapping("/attendance/excel")
    public ExcelResponse getAttendanceExcel() throws IOException {;
        return attendacneExcelService.generateExcel();
    }
}