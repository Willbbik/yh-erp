package com.yh.erp.infrastructure.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file-config")
@AllArgsConstructor
@Getter
public class FileProperties {
    private String uploadPath;
}
