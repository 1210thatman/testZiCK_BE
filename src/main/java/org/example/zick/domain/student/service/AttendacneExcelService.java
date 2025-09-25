package org.example.zick.domain.student.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.zick.domain.student.domain.AttendanceLog;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendacneExcelService {
    public static ResponseEntity<Map<String, String>> exportToExcel(List<AttendanceLog> logs) throws IOException {
        //급식 신청자 필터링하기
        List<AttendanceLog> filtered = logs.stream()
                .filter(log -> Boolean.TRUE.equals(log.getUser().getApplied()))
                .toList();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("출석 명단");

        //엑셀 헤더
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("학번");
        header.createCell(1).setCellValue("아침");
        header.createCell(2).setCellValue("점심");
        header.createCell(3).setCellValue("저녁");

        //색상은 차후 수정될 수 있음
        CellStyle[] gradeStyles = new CellStyle[3];
        IndexedColors[] colors = {
                IndexedColors.LIGHT_YELLOW,
                IndexedColors.LIGHT_GREEN,
                IndexedColors.LIGHT_BLUE,
        };

        for(int i=0; i < 3; i++){
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(colors[i].getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            gradeStyles[i] = style;
        }

        //학번 기준으로 데이터 매핑
        Map<String, List<AttendanceLog>> grouped = filtered.stream().collect(Collectors.groupingBy(log -> log.getUser().getStudentNumber()));

        int rowIdx = 1;
        for(Map.Entry<String, List<AttendanceLog>> entry : grouped.entrySet()){
            String studentNumber = entry.getKey();
            List<AttendanceLog> attendanceLogs = entry.getValue();

            Row row  = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(studentNumber);

            //학번으로부터 학년 추출
            int grade = Character.getNumericValue(studentNumber.charAt(0));
            if(grade >= 1 && grade <= 3){
                for(int col = 0; col <= 3; col++){
                    Cell cell = row.getCell(col);
                    if(cell == null) {
                        cell = row.createCell(col);
                    }
                    cell.setCellStyle(gradeStyles[grade-1]);
                }
            }

            //출석 표시
            for(AttendanceLog log : attendanceLogs){
                switch (log.getMealType()){
                    case BREAKFAST -> row.createCell(1).setCellValue("O");
                    case LUNCH ->  row.createCell(2).setCellValue("O");
                    case DINNER ->  row.createCell(3).setCellValue("O");
                }
            }
        }

        for(int i=0; i<=3; i++){
            sheet.autoSizeColumn(i);
        }

        //Base64로 변환
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            workbook.write(bos);
            workbook.close();
            String base64Excel = Base64.getEncoder().encodeToString(bos.toByteArray());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attendance.xlsx")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("file", base64Excel));
        }
    }
}
