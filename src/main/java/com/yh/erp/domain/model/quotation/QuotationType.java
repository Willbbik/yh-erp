package com.yh.erp.domain.model.quotation;

import com.yh.erp.domain.shared.EnumCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuotationType implements EnumCode {

    NORMAL("A", "일반 견적서"),
    SUPPORT("B", "조달 견적서");

    private final String code;
    private final String name;
}
