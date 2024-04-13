package com.yh.erp.infrastructure.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FirebaseSelectorTest {

    @Autowired
    private FirebaseSelector firebaseSelector;

    @Test
    void selectUserById() throws Exception {

        //given
        ApiFuture<DocumentSnapshot> result = firebaseSelector.selectUserById("admin");

        //when
        DocumentSnapshot documentSnapshot = result.get();

        //then
        assertNotNull(result);
        assertNotNull(documentSnapshot.get("password"));
    }
}