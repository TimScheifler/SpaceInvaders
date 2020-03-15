package com.madproject.spaceinvaders.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.madproject.spaceinvaders.models.components.PlayerScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirebaseHelper {

    private FirebaseFirestore databaseReference;
    private SharedPreferencesHandler sharedPreferencesHandler;

    private final String name = "name";
    private final String wave = "wave";
    private final String score = "score";

    private ArrayList<PlayerScore> results;

    private String COLLECTION_PATH = "test_db";

    public FirebaseHelper(Context context){
        sharedPreferencesHandler = new SharedPreferencesHandler(context);
        databaseReference = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        databaseReference.setFirestoreSettings(settings);
        prepareDataFromFirebase();
    }

    public ArrayList<PlayerScore> getScoretableFromFirebase(){
        return results;
    }

    public void uploadScore(int wave, int score){

        Map<String, Object> values = new HashMap<>();
        values.put(name, sharedPreferencesHandler.getSharedPrefsPlayerName());
        values.put(this.wave, wave);
        values.put(this.score,score);

        databaseReference.collection(COLLECTION_PATH).add(values)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("Success:","DocumentSnapshot added with ID:"+documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Error:","Error adding document",e);
            }
        });
    }

    private void prepareDataFromFirebase(){
        results = new ArrayList<>();
        databaseReference.collection(COLLECTION_PATH).orderBy("score", Query.Direction.DESCENDING).limit(25)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                                results.add(new PlayerScore(Objects.requireNonNull(documentSnapshot.getData().get(name)).toString(),
                                        Integer.parseInt(Objects.requireNonNull(documentSnapshot.getData().get(wave)).toString()), Integer.parseInt(documentSnapshot.getData().get(score).toString())));
                            }
                        } else {
                            Log.i("Error", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
