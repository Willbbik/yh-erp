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
import java.util.List;

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

        //헤더
        createTitleHeader(workbook, sheet);
        createFirstHeader(sheet);
        createSecondHeader(sheet);
        createThreeHeader(sheet);

        //제목, 설명, 내역서 라인

    }

    private static void createTitleHeader(Workbook workbook, Sheet sheet) {
        CellStyle headerCellStyle = getHeaderCellStyle(workbook);

        Row row = sheet.createRow(0);
        row.setHeightInPoints(50);
        Cell cell = row.createCell(0);
        cell.setCellValue("견적서 (조달)");
        cell.setCellStyle(headerCellStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
    }


    private static void createFirstHeader(Sheet sheet) {
        //###### 일자라인
        Row row1 = sheet.createRow(1);
        Row row2 = sheet.createRow(2);
        Row row3 = sheet.createRow(3);
        List<Row> rows = List.of(row1, row2, row3);

        //일자
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("일자");
        sheet.addMergedRegion(new CellRangeAddress(1, 3, 0, 1));

        //일자값
        Cell cell2 = row1.createCell(2);
        cell2.setCellValue("2023년 11월 20일");
        sheet.addMergedRegion(new CellRangeAddress(1, 3, 2, 3));

        //사업자등록번호
        Cell cell5 = row1.createCell(4);
        cell5.setCellValue("사업자등록번호");

        //사업자등록번호 값
        Cell cell6 = row1.createCell(5);
        cell6.setCellValue("206-81-15871");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 6));

        //상호
        Cell cell7 = row2.createCell(4);
        cell7.setCellValue("상호");

        //상호값
        Cell cell8 = row2.createCell(5);
        cell8.setCellValue("(주) 유한정공");
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 5, 6));

        //대표자
        Cell cell9 = row3.createCell(4);
        cell9.setCellValue("대표자");

        //대표자값
        Cell cell10 = row3.createCell(5);
        cell10.setCellValue("옥영수");
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 6));

        //인감
        row1.createCell(7);
        row2.createCell(7);
        row3.createCell(7);
        sheet.addMergedRegion(new CellRangeAddress(1, 3, 7, 7));


        CellStyle cellBorder = getCellBorder(sheet.getWorkbook());

        //각 셀마다 테두리 만들기
        for(Row row : rows) {
            row.setHeightInPoints(29);

            for(int i=0; i<row.getLastCellNum(); i++) {
//                row.getCell(i).setCellStyle(cellBorder);
            }
        }
    }

    private static void createSecondHeader(Sheet sheet) {
        Row row4 = sheet.createRow(4);
        Row row5 = sheet.createRow(5);
        Row row6 = sheet.createRow(6);
        Row row7 = sheet.createRow(7);
        List<Row> rows = List.of(row4, row5, row6, row7);

        //수신
        Cell cell1 = row4.createCell(0);
        cell1.setCellValue("수신");
        sheet.addMergedRegion(new CellRangeAddress(4, 5, 0, 1));

        //수신값
        Cell cell2 = row4.createCell(2);
        cell2.setCellValue("천안 부대초등학교");
        sheet.addMergedRegion(new CellRangeAddress(4, 5, 2, 3));

        //사업장소재지
        Cell cell3= row4.createCell(4);
        cell3.setCellValue("사업장소재지");

        //사업장소재지 값
        Cell cell4 = row4.createCell(5);
        cell4.setCellValue("서울시 성동구 성수이로 18길 32-1");
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 5, 7));

        //업태및종목
        Cell cell5 = row5.createCell(4);
        cell5.setCellValue("업태및종목");

        //업태및종목 값
        Cell cell6 = row5.createCell(5);
        cell6.setCellValue("제조 · 도소매 / 주방기구 · 주방용품");
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 5, 7));

        //견적금액
        Cell cell7 = row6.createCell(0);
        cell7.setCellValue("견적금액");
        sheet.addMergedRegion(new CellRangeAddress(6, 7, 0, 1));

        //견적금액 한글값
        Cell cell10 = row6.createCell(2);
        cell10.setCellValue("일금이천이백일십육만원정");
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 3));

        //견적금액 숫자값
        Cell cell11 = row7.createCell(2);
        cell11.setCellValue("(₩22,160,000)");
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 2, 3));

        //전화번호
        Cell cell12 = row6.createCell(4);
        cell12.setCellValue("전화번호");

        //전화번호 값
        Cell cell13 = row6.createCell(5);
        cell13.setCellValue("02-465-8555(대)");

        //FAX
        Cell cell14 = row6.createCell(6);
        cell14.setCellValue("FAX");

        //FAX 값
        Cell cell15 = row6.createCell(7);
        cell15.setCellValue("02-465-1314");

        //E-Mail
        Cell cell16 = row7.createCell(4);
        cell16.setCellValue("E-Mail");

        //E-Mail 값
        Cell cell17 = row7.createCell(5);
        cell17.setCellValue("yh21cc@naver.com");
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 5, 7));

        CellStyle cellBorder = getCellBorder(sheet.getWorkbook());

        //각 셀마다 테두리 만들기
        for(Row row : rows) {
            row.setHeightInPoints(29);
            for(int i=0; i<row.getLastCellNum(); i++) {
//                row.getCell(i).setCellStyle(getHeaderCellStyle(sheet.getWorkbook()));
            }
        }
    }

    private static void createThreeHeader(Sheet sheet) {
        Row row8 = sheet.createRow(8);
        Row row9 = sheet.createRow(9);
        Row row10 = sheet.createRow(10);
        List<Row> rows = List.of(row8, row9, row10);

        //제목
        Cell cell1 = row8.createCell(0);
        cell1.setCellValue("제목");
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 0, 1));

        //제목값
        Cell cell2 = row8.createCell(2);
        cell2.setCellValue("학교급식 현대화사업 급식기구 선정자료");
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 2, 7));

        //설명
        Cell cell3= row9.createCell(0);
        cell3.setCellValue("아래와 같이 견적서를 제출합니다.");
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 7));

        //내역서
        row10.setHeightInPoints(50);
        Cell cell4 = row10.createCell(0);
        cell4.setCellValue("내역서");
        cell4.setCellStyle(getHeaderCellStyle(sheet.getWorkbook()));
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 0, 7));

        //각 셀마다 테두리 만들기
        for(Row row : rows) {
            row.setHeightInPoints(40);
            for(int i=0; i<row.getLastCellNum(); i++) {
//                row.getCell(i).setCellStyle(getHeaderCellStyle(sheet.getWorkbook()));
            }
        }
    }

    private static CellStyle getCellBorder(Workbook workbook) {
        CellStyle borderedStyle = workbook.createCellStyle();
        borderedStyle.setBorderTop(BorderStyle.THIN);
        borderedStyle.setBorderBottom(BorderStyle.THIN);
        borderedStyle.setBorderLeft(BorderStyle.THIN);
        borderedStyle.setBorderRight(BorderStyle.THIN);

        return borderedStyle;
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
