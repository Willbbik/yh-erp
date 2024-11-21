package com.yh.erp.interfaces.controller;

import com.yh.erp.domain.model.quotation.dto.ProcQuotationCreateDto;
import com.yh.erp.infrastructure.excel.ProcurementQuotationExcelGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @GetMapping("/temp")
    public String temp(){
        return "success";
    }


    @PostMapping("/temp/excel")
    public ResponseEntity<byte[]> getTempExcel(@RequestBody ProcQuotationCreateDto dto){
        return ProcurementQuotationExcelGenerator.createExcel("연습", dto);
    }

}
