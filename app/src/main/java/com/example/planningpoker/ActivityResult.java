package com.example.planningpoker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.planningpoker.model.Answer;
import com.example.planningpoker.model.Question;
import com.example.planningpoker.model.Session;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;

public class ActivityResult extends AppCompatActivity {

    private TextView nameView, answerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        nameView = findViewById(R.id.username);
        answerView = findViewById(R.id.answer);

        Intent intent = getIntent();
        final String sess_id = intent.getStringExtra("session_id");
        final String name = intent.getStringExtra("user_name");
        nameView.setText(name);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference reff = db.collection("sessions").document(sess_id);

        reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Session sess = documentSnapshot.toObject(Session.class);
                ArrayList<Question> questions = sess.getQuestions();
                Question question = questions.get(0);

                for (Answer answer : question.getAnswers()) {
                    if (answer.getUser().getName().equals(name)) {
                        answerView.setText(answer.getContent());
                    }
                }
            }
        });
    }
}
