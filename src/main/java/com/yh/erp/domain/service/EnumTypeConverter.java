package com.yh.erp.domain.service;

import com.yh.erp.domain.shared.EnumCode;
import jakarta.persistence.AttributeConverter;

import java.lang.reflect.ParameterizedType;
import java.util.stream.Stream;

public class EnumTypeConverter<T extends EnumCode> implements AttributeConverter<T, String>{

    private Class<T> clazz;

    protected EnumTypeConverter() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (attribute == null){
            return null;
        }

        return attribute.getCode();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }

        return Stream.of(clazz.getEnumConstants())
                .filter(e -> e.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
    }
}
