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
import com.madproject.spaceinvaders.SharedPreferencesHandler;
import com.madproject.spaceinvaders.models.components.PlayerScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {

    private FirebaseFirestore databaseReference;
    private SharedPreferencesHandler sharedPreferencesHandler;

    private Context context;

    private ArrayList<PlayerScore> results;

    private String COLLECTION_PATH = "test_db";

    public FirebaseHelper(Context context){
        this.context = context;
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
        values.put("name", sharedPreferencesHandler.getSharedPrefsPlayerName());
        values.put("wave", wave);
        values.put("score",score);

        databaseReference.collection(COLLECTION_PATH).add(values)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("Tim: Success:","DocumentSnapshot added with ID:"+documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Tim: Error:","Error adding document",e);
            }
        });
    }

    private void prepareDataFromFirebase(){
        results = new ArrayList<>();
        databaseReference.collection(COLLECTION_PATH).orderBy("score", Query.Direction.DESCENDING).limit(10)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                results.add(new PlayerScore(documentSnapshot.getData().get("name").toString(),
                                        Integer.parseInt(documentSnapshot.getData().get("wave").toString()), Integer.parseInt(documentSnapshot.getData().get("score").toString())));
                            }
                            Log.i("RESULTS-SIZE", results.size() + "");
                        } else {
                            Log.i("Tim: Error:", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
