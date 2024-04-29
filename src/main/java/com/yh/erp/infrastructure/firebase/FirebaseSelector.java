package com.yh.erp.infrastructure.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FirebaseSelector {

    private final FirebaseProperties firebaseProperties;

    public ApiFuture<DocumentSnapshot> selectUserById(String userId){
        Firestore db = FirestoreClient.getFirestore();

        return db.collection(firebaseProperties.getUserCollectionName())
                .document(userId).get();
    }


    public Optional<FirebaseUser> getUserById(String userId) throws Exception {

        Firestore db = FirestoreClient.getFirestore();

        DocumentSnapshot documentSnapshot = db
                .collection(firebaseProperties.getUserCollectionName())
                .document(userId)
                .get()
                .get();

        if(!documentSnapshot.exists()){
            return Optional.empty();
        }

        FirebaseUser firebaseUser = FirebaseUser.builder()
                .userId(userId)
                .password((String) documentSnapshot.get("password"))
                .name((String) documentSnapshot.get("name"))
                .createAt(documentSnapshot.getCreateTime().toSqlTimestamp().toLocalDateTime())
                .build();

        return Optional.of(firebaseUser);
    }

}
