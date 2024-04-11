package com.yh.erp.domain.shared;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum YesOrNo implements EnumCode{

    YES("Y", "예"),
    NO("N", "아니");

    private final String code;
    private final String name;

}
