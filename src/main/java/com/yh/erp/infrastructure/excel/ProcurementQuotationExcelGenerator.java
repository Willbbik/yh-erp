package com.yh.erp.infrastructure.excel;

import com.yh.erp.domain.model.quotation.dto.ProcQuotationCreateDto;
import com.yh.erp.domain.model.quotation.dto.QuotationProductInfo;
import com.yh.erp.infrastructure.error.YhErpException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * 견적서(조달) 생성 클래스
 *
 */
public class ProcurementQuotationExcelGenerator {

    private static final MediaType XLSX_TYPE = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    private static final Integer PROCUREMENT_QUOTATION_ROW_START_INDEX = 2;

    private static final Integer DEFAULT_ROW_LENGTH = 15;

    private static final Integer DEFAULT_CELL_LENGTH = 8;

    public static ResponseEntity<byte[]> createExcel(String excelName, ProcQuotationCreateDto dto) {
        try {
            // 엑셀 생성
            Workbook workbook = new SXSSFWorkbook();

            // 시트 생성
            Sheet sheet = createSheet(workbook);

            // 헤더 생성
            createHeader(sheet, dto);

            // 바디 생성
            createBody(sheet, dto.getProductInfos());

            //엑셀 반환
            return getResponseEntity(excelName, workbook);
        } catch (Exception e) {
            throw new YhErpException("엑셀 생성 중 에러가 발생하였습니다. 에러메시지: ", e.getMessage());
        }
    }

    //시트 생성 및 사이즈 설정
    private static Sheet createSheet(Workbook workbook) {

        CellStyle headerCellStyle = getHeaderCellStyle(workbook, (short) 16);
        Sheet sheet = workbook.createSheet();
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);

        //행
        for(int i=0; i<=DEFAULT_ROW_LENGTH; i++) {
            Row row = sheet.createRow(i);

            //열
            for(int j=0; j<DEFAULT_CELL_LENGTH; j++) {
                row.createCell(j).setCellStyle(headerCellStyle);
            }
        }

        CellRangeAddress cellAddresses = new CellRangeAddress(0, 1, 0, 7);
        sheet.addMergedRegion(cellAddresses);

        CellRangeAddress borderRegion = new CellRangeAddress(PROCUREMENT_QUOTATION_ROW_START_INDEX, PROCUREMENT_QUOTATION_ROW_START_INDEX+11, 0, 7);

        //테두리
        RegionUtil.setBorderTop(BorderStyle.THIN, borderRegion, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, borderRegion, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, borderRegion, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, borderRegion, sheet);

        //테두리 선
        RegionUtil.setTopBorderColor(IndexedColors.BLACK.index, borderRegion, sheet);
        RegionUtil.setBottomBorderColor(IndexedColors.BLACK.index, borderRegion, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.BLACK.index, borderRegion, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.BLACK.index, borderRegion, sheet);

        // Set column widths
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 7000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 5500);
        sheet.setColumnWidth(5, 8000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 4400);

        return sheet;
    }

    //헤더 생성
    private static void createHeader(Sheet sheet, ProcQuotationCreateDto dto) {
        //제목
        createTitleHeader(sheet);
        //일자
        createFirstHeader(sheet, dto);
        //수신
        createSecondHeader(sheet, dto);
        //제목 ~ 내역서 제목
        createThreeHeader(sheet, dto);
        //데이터 헤더
        createBodyHeader(sheet);
    }

    private static void createTitleHeader(Sheet sheet) {
        Workbook workbook = sheet.getWorkbook();

        Row row = sheet.getRow(PROCUREMENT_QUOTATION_ROW_START_INDEX);
        row.setHeightInPoints(50);
        Cell cell = row.getCell(0);
        cell.setCellValue("견적서 (조달)");
        cell.setCellStyle(getHeaderCellStyle(workbook, (short) 26));

        sheet.addMergedRegion(new CellRangeAddress(PROCUREMENT_QUOTATION_ROW_START_INDEX, PROCUREMENT_QUOTATION_ROW_START_INDEX, 0, 7));
    }

    //일자 라인
    private static void createFirstHeader(Sheet sheet, ProcQuotationCreateDto dto) {
        int i1 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 1;
        int i2 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 2;
        int i3 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 3;

        Row row1 = sheet.getRow(i1);
        Row row2 = sheet.getRow(i2);
        Row row3 = sheet.getRow(i3);
        List<Row> rows = List.of(row1, row2, row3);

        //일자
        Cell cell1 = row1.getCell(0);
        Cell cell2 = row1.getCell(2);
        cell1.setCellValue("일자");
        cell2.setCellValue(dto.getQuoteDate()); //ex) 2023년 11월 20일
        sheet.addMergedRegion(new CellRangeAddress(i1, i3, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(i1, i3, 2, 3));

        //사업자등록번호
        Cell cell5 = row1.getCell(4);
        Cell cell6 = row1.getCell(5);
        cell5.setCellValue("사업자등록번호");
        cell6.setCellValue(dto.getBusinessNumber()); //ex) 206-81-15871
        sheet.addMergedRegion(new CellRangeAddress(i1, i1, 5, 6));

        //상호
        Cell cell7 = row2.getCell(4);
        Cell cell8 = row2.getCell(5);
        cell7.setCellValue("상호");
        cell8.setCellValue(dto.getCompanyName()); //ex) (주) 유한정공
        sheet.addMergedRegion(new CellRangeAddress(i2, i2, 5, 6));

        //대표자
        Cell cell9 = row3.getCell(4);
        Cell cell10 = row3.getCell(5);
        cell9.setCellValue("대표자");
        cell10.setCellValue(dto.getOwnerName()); //ex) 옥영수
        sheet.addMergedRegion(new CellRangeAddress(i3, i3, 5, 6));

        //인감
        row1.getCell(7);
        row2.getCell(7);
        row3.getCell(7);
        sheet.addMergedRegion(new CellRangeAddress(i1, i3, 7, 7));

//        CellStyle cellBorder = getCellBorder(sheet.getWorkbook());

        //각 셀마다 테두리 만들기
        for(Row row : rows) {
            row.setHeightInPoints(29);

//            for(int i=0; i<row.getLastCellNum(); i++) {
//                row.getCell(i).setCellStyle(cellBorder);
//            }
        }
    }

    //수신 라인
    private static void createSecondHeader(Sheet sheet, ProcQuotationCreateDto dto) {
        int i4 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 4;
        int i5 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 5;
        int i6 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 6;
        int i7 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 7;

        Row row4 = sheet.getRow(i4);
        Row row5 = sheet.getRow(i5);
        Row row6 = sheet.getRow(i6);
        Row row7 = sheet.getRow(i7);
        List<Row> rows = List.of(row4, row5, row6, row7);

        //수신
        Cell cell1 = row4.getCell(0);
        Cell cell2 = row4.getCell(2);
        cell1.setCellValue("수신");
        cell2.setCellValue(dto.getCustomerName()); //ex) 천안 부대초등학교
        sheet.addMergedRegion(new CellRangeAddress(i4, i5, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(i4, i5, 2, 3));

        //사업장소재지
        Cell cell3= row4.getCell(4);
        Cell cell4 = row4.getCell(5);
        cell3.setCellValue("사업장소재지");
        cell4.setCellValue(dto.getCompanyLocation()); //ex) 서울시 성동구 성수이로 18길 32-1
        sheet.addMergedRegion(new CellRangeAddress(i4, i4, 5, 7));

        //업태및종목
        Cell cell5 = row5.getCell(4);
        Cell cell6 = row5.getCell(5);
        cell5.setCellValue("업태및종목");
        cell6.setCellValue(dto.getCompanyType()); //ex) 제조 · 도소매 / 주방기구 · 주방용품
        sheet.addMergedRegion(new CellRangeAddress(i5, i5, 5, 7));

        //견적금액 한글
        Cell cell7 = row6.getCell(0);
        Cell cell8 = row6.getCell(2);
        cell7.setCellValue("견적금액");
        cell8.setCellValue(dto.getStrTotalPrice()); //ex) 일금이천이백일십육만원정
        sheet.addMergedRegion(new CellRangeAddress(i6, i7, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(i6, i6, 2, 3));

        //견적금액 숫자
        Cell cell9 = row7.getCell(2);
        cell9.setCellValue(dto.getTotalPrice()); //ex) (₩22,160,000)
        sheet.addMergedRegion(new CellRangeAddress(i7, i7, 2, 3));

        //전화번호
        Cell cell10 = row6.getCell(4);
        Cell cell11 = row6.getCell(5);
        cell10.setCellValue("전화번호");
        cell11.setCellValue(dto.getPhoneNumber()); //ex) 02-465-8555(대)

        //FAX
        Cell cell12 = row6.getCell(6);
        Cell cell13 = row6.getCell(7);
        cell12.setCellValue("FAX");
        cell13.setCellValue(dto.getFaxNumber()); //ex) 02-465-1314

        //E-Mail
        Cell cell14 = row7.getCell(4);
        Cell cell15 = row7.getCell(5);
        cell14.setCellValue("E-Mail");
        cell15.setCellValue(dto.getEmail()); //ex) yh21cc@naver.com
        sheet.addMergedRegion(new CellRangeAddress(i7, i7, 5, 7));

//        CellStyle cellBorder = getCellBorder(sheet.getWorkbook());

        //각 셀마다 테두리 만들기
        for(Row row : rows) {
            row.setHeightInPoints(29);
//            for(int i=0; i<row.getLastCellNum(); i++) {
//                row.getCell(i).setCellStyle(getHeaderCellStyle(sheet.getWorkbook()));
//            }
        }
    }

    //제목 ~ 내역서 제목 라인
    private static void createThreeHeader(Sheet sheet, ProcQuotationCreateDto dto) {
        int i8 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 8;
        int i9 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 9;
        int i10 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 10;

        Row row8 = sheet.getRow(i8);
        Row row9 = sheet.getRow(i9);
        Row row10 = sheet.getRow(i10);
        List<Row> rows = List.of(row8, row9, row10);

        //제목
        Cell cell1 = row8.getCell(0);
        Cell cell2 = row8.getCell(2);
        cell1.setCellValue("제목");
        cell2.setCellValue(dto.getTitle()); //학교급식 현대화사업 급식기구 선정자료
        sheet.addMergedRegion(new CellRangeAddress(i8, i8, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(i8, i8, 2, 7));

        //설명
        Cell cell3= row9.getCell(0);
        Cell cell4 = row10.getCell(0);
        cell3.setCellValue("아래와 같이 견적서를 제출합니다.");
        cell4.setCellValue("내역서");
        sheet.addMergedRegion(new CellRangeAddress(i9, i9, 0, 7));
        sheet.addMergedRegion(new CellRangeAddress(i10, i10, 0, 7));

        cell4.setCellStyle(getHeaderCellStyle(sheet.getWorkbook(), (short) 26));
        row10.setHeightInPoints(50);

        //각 셀마다 테두리 만들기
        for(Row row : rows) {
            row.setHeightInPoints(40);
//            for(int i=0; i<row.getLastCellNum(); i++) {
//                row.getCell(i).setCellStyle(getHeaderCellStyle(sheet.getWorkbook()));
//            }
        }
    }

    //상품 헤더
    private static void createBodyHeader(Sheet sheet) {
        int i11 = PROCUREMENT_QUOTATION_ROW_START_INDEX + 11;

        Row row11 = sheet.getRow(i11);
        row11.setHeightInPoints(29);

        List<String> headers = List.of("순번", "제품사진", "규격명/품명", "규격", "수량", "단가", "금액", "식별번호");

        for(int i=0; i<sheet.getDefaultColumnWidth(); i++){
            Cell cell = row11.getCell(i);
            cell.setCellValue(headers.get(i));
        }
    }

    //데이터 목록
    private static void createBody(Sheet sheet, List<QuotationProductInfo> productInfos) throws IllegalAccessException {

        //로우 시작 번호.
        int startRowIndex = PROCUREMENT_QUOTATION_ROW_START_INDEX + 12;

        for(int i=1; i<=productInfos.size(); i++) {
            QuotationProductInfo productInfo = productInfos.get(--i);
            Row row = sheet.createRow(startRowIndex++);
            row.setHeightInPoints(90);

            //순번
            Cell cell0 = row.createCell(0);
            renderCellValue(cell0, i);

            //이미지
            Cell cell1 = row.createCell(1);
            renderCellValue(cell1, productInfo.getImagePath());

            //규격명/품명
            Cell cell2 = row.createCell(2);
            renderCellValue(cell2, productInfo.getModelName());

            //규격
            Cell cell3 = row.createCell(3);
            renderCellValue(cell3, productInfo.getSize());

            //수량
            Cell cell4 = row.createCell(4);
            renderCellValue(cell4, productInfo.getQuantity());

            //단가
            Cell cell5 = row.createCell(5);
            renderCellValue(cell5, productInfo.getPrice());

            //금액
            Cell cell6 = row.createCell(6);
            renderCellValue(cell6, productInfo.getTotalPrice());

            //식별번호
            Cell cell7 = row.createCell(7);
            renderCellValue(cell7, productInfo.getG2bNumber());
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

    private static CellStyle getHeaderCellStyle(Workbook workbook, short fontSize) {
        // Create a bold font for headers
        Font boldFont = workbook.createFont();
        boldFont.setFontHeightInPoints(fontSize);
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

    //셀 데이터 적용. 각 셀 데이터 타입이 달라질 수 있어서.
    private static void renderCellValue(Cell cell, Object cellValue) {
        //이외에 날짜, boolean 등등 필요에 따라 추가하면 됨
        if (cellValue instanceof Number) {
            Number numberValue = (Number) cellValue;
            cell.setCellValue(numberValue.doubleValue());
            return;
        }

        cell.setCellValue(cellValue == null ? "" : cellValue.toString());
    }

}
