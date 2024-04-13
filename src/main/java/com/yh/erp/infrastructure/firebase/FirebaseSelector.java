package com.yh.erp.infrastructure.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FirebaseSelector {

    private final FirebaseProperties firebaseProperties;

    public ApiFuture<DocumentSnapshot> selectUserById(String userId){
        Firestore db = FirestoreClient.getFirestore();

        return db.collection(firebaseProperties.getUserCollectionName())
                .document(userId).get();
    }


}
