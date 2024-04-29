package com.yh.erp.infrastructure.firebase;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class FirebaseUser {

    private String userId;
    private String password;
    private String name;
    private LocalDateTime createAt;

    @Builder
    public FirebaseUser(String userId, String password, String name, LocalDateTime createAt) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.createAt = createAt;
    }
}
