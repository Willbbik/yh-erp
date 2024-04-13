package com.yh.erp.infrastructure.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Configuration
@RequiredArgsConstructor
public class FirebaseInit {

    private final FirebaseProperties firebaseProperties;

    @PostConstruct
    public void init(){
        try {
            FileInputStream serviceAccount = new FileInputStream(firebaseProperties.getServiceKeyPath());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(firebaseProperties.getDatabaseUrl())
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
