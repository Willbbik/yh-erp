package com.yh.erp.interfaces.controller;

import com.yh.erp.infrastructure.excel.ProcurementQuotationExcelGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @GetMapping("/temp")
    public String temp(){
        return "success";
    }


    @GetMapping("/temp/excel")
    public ResponseEntity<byte[]> getTempExcel(){
        return ProcurementQuotationExcelGenerator.createExcel("연습");
    }

}
