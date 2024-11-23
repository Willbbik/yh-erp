package com.yh.erp.application;

import com.yh.erp.domain.model.quotation.dto.ProcQuotationCreateDto;
import org.springframework.http.ResponseEntity;

public interface QuotationService {

    ResponseEntity<byte[]> createProcQuotation(ProcQuotationCreateDto dto);
}
