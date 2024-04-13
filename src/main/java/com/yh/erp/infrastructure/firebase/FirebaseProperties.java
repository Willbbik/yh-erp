package com.yh.erp.infrastructure.firebase;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "firebase-config")
@Validated
@Data
public class FirebaseProperties {

    private String databaseUrl;
    private String serviceKeyPath;
    private String userCollectionName;

}
