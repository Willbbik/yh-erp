package com.yh.erp.application.impl;

import com.yh.erp.application.QuotationService;
import com.yh.erp.domain.model.quotation.dto.ProcQuotationCreateDto;
import com.yh.erp.infrastructure.excel.ProcurementQuotationExcelGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuotationServiceImpl implements QuotationService {

    @Override
    public ResponseEntity<byte[]> createProcQuotation(ProcQuotationCreateDto dto) {
        //TODO 이벤트 리스너 이용해서 로그 및 db에 이력 저장 해야됨

        return ProcurementQuotationExcelGenerator.createExcel("연습", dto);
    }

}
