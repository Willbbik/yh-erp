package com.yh.erp.interfaces.controller;

import com.yh.erp.application.QuotationService;
import com.yh.erp.domain.model.quotation.dto.ProcQuotationCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationService quotationService;

    @PostMapping("/api/quotation/procurement/create")
    public ResponseEntity<byte[]> createProcurementQuotation(@RequestBody ProcQuotationCreateDto dto) {
        return quotationService.createProcQuotation(dto);
    }

}
