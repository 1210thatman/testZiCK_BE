package org.example.zick.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.zick.domain.student.domain.AttendanceLog;
import org.example.zick.domain.student.domain.repository.AttendanceLogRepository;
import org.example.zick.domain.student.persistence.response.ExcelResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendacneExcelService {

    private final AttendanceLogRepository attendanceLogRepository;

    @Transactional(readOnly = true)
    public ExcelResponse generateExcel() throws IOException {
        // 신청 완료된 사용자 로그만
        List<AttendanceLog> logs = attendanceLogRepository.findAllByUser_AppliedTrue();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("출석 명단");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("학번");
        header.createCell(1).setCellValue("아침");
        header.createCell(2).setCellValue("점심");
        header.createCell(3).setCellValue("저녁");

        // 배경색은 임의로 지정함, 수정가능
        CellStyle[] gradeStyles = new CellStyle[3];
        IndexedColors[] colors = {
                IndexedColors.LIGHT_YELLOW,
                IndexedColors.LIGHT_GREEN,
                IndexedColors.LIGHT_BLUE,
        };
        for (int i = 0; i < 3; i++) {
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(colors[i].getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            gradeStyles[i] = style;
        }

        Map<String, List<AttendanceLog>> grouped =
                logs.stream().collect(Collectors.groupingBy(log -> log.getUser().getStudentNumber()));

        // 학년 -> 반 -> 번호 순으로 정렬
        List<String> sortedStudentNumbers = grouped.keySet().stream()
                .sorted((a, b) -> {
                    int gradeA = Character.getNumericValue(a.charAt(0));
                    int gradeB = Character.getNumericValue(b.charAt(0));
                    if (gradeA != gradeB) return Integer.compare(gradeA, gradeB);

                    int classA = Character.getNumericValue(a.charAt(1));
                    int classB = Character.getNumericValue(b.charAt(1));
                    if (classA != classB) return Integer.compare(classA, classB);

                    int numA = Integer.parseInt(a.substring(2));
                    int numB = Integer.parseInt(b.substring(2));
                    return Integer.compare(numA, numB);
                })
                .toList();

        int rowIdx = 1;
        for (String studentNumber : sortedStudentNumbers) {
            List<AttendanceLog> attendanceLogs = grouped.get(studentNumber);

            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(studentNumber);

            int grade = Character.getNumericValue(studentNumber.charAt(0));
            if (grade >= 1 && grade <= 3) {
                for (int col = 0; col <= 3; col++) {
                    Cell cell = row.getCell(col);
                    if (cell == null) cell = row.createCell(col);
                    cell.setCellStyle(gradeStyles[grade - 1]);
                    if (col != 0) cell.setCellValue(" ");
                }
            }

            // 출석 표시
            for (AttendanceLog log : attendanceLogs) {
                switch (log.getMealType()) {
                    case BREAKFAST -> row.getCell(1).setCellValue("O");
                    case LUNCH -> row.getCell(2).setCellValue("O");
                    case DINNER -> row.getCell(3).setCellValue("O");
                }
            }
        }

        // 컬럼 자동 너비 조정
        for (int i = 0; i <= 3; i++) {
            sheet.autoSizeColumn(i);
        }
        
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            workbook.close();
            String base64Excel = Base64.getEncoder().encodeToString(bos.toByteArray());
            return new ExcelResponse("attendance.xlsx", base64Excel);
        }
    }
}