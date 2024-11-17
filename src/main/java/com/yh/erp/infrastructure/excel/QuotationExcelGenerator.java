package com.yh.erp.infrastructure.excel;

import com.yh.erp.infrastructure.error.YhErpException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class QuotationExcelGenerator {

    private static final MediaType XLSX_TYPE = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");


    public static ResponseEntity<byte[]> createPrecocityQuotationExcel(String excelName) {
        try {

            // 엑셀 생성
            Workbook workbook = new XSSFWorkbook();

            // 시트 생성
            Sheet sheet = createPrecocitySheet(workbook);
//            Sheet sheet = workbook.createSheet();
//            sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);

            // 헤더 생성
            createPrecocityHeader(workbook, sheet);

            return getResponseEntity(excelName, workbook);

        } catch (Exception e) {
            throw new YhErpException("엑셀 생성 중 에러가 발생하였습니다.");
        }
    }

    private static Sheet createPrecocitySheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet();
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);

        // Set column widths
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 8000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 8000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 8000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 8000);

        return sheet;
    }


    private static void createPrecocityHeader(Workbook workbook, Sheet sheet) {

        CellStyle headerCellStyle = getHeaderCellStyle(workbook);

        //견적서(조달) 라인

        Row row = sheet.createRow(0);
        row.setHeightInPoints(50);
        Cell cell = row.createCell(0);
        cell.setCellValue("견적서 (조달)");
        cell.setCellStyle(headerCellStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

        //일자라인



        //수신라인


        //견적금액라인


        //제목, 설명, 내역서 라인


    }


    private static CellStyle getHeaderCellStyle(Workbook workbook) {
        // Create a bold font for headers
        Font boldFont = workbook.createFont();
        boldFont.setFontHeightInPoints((short) 26);
        boldFont.setFontName("순명조");
        boldFont.setBold(true);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(boldFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        return headerStyle;
    }

    private static ResponseEntity<byte[]> getResponseEntity(String excelName, Workbook workbook) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        byte[] excelContent = stream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(XLSX_TYPE);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(excelName + ".xlsx", StandardCharsets.UTF_8.toString()));
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent);
    }

}
