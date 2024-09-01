package com.yh.erp.domain.shared;

import com.yh.erp.domain.service.EnumTypeConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum YesOrNo implements EnumCode{

    YES("Y", "예"),
    NO("N", "아니");

    private final String code;
    private final String name;

    public static boolean isYes(String value){
        return YES.getCode().equals(value);
    }

    public static boolean isNo(String value){
        return NO.getCode().equals(value);
    }

    public static class StringTo extends EnumTypeConverter<YesOrNo> {};

}
