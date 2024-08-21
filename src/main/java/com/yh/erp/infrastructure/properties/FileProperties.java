package com.yh.erp.infrastructure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "file-config")
@Validated
@Data
public class FileProperties {
    private String uploadPath;
}
