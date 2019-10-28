package com.example.planningpoker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class Questions extends AppCompatActivity {


    RecyclerViewAdapter adapter;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        RecyclerView recyclerView2 = findViewById(R.id.my_recycler_view2);
        RecyclerView recyclerView3 = findViewById(R.id.my_recycler_view3);
        sendButton = findViewById(R.id.sendButton);

        int numberOfColumns = 5;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerView2.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerView3.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        adapter = new RecyclerViewAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter);
        recyclerView3.setAdapter(adapter);

        findViewById(R.id.masodikkerdes).setVisibility(View.GONE);
        findViewById(R.id.my_recycler_view2).setVisibility(View.GONE);
        findViewById(R.id.harmadikkerdes).setVisibility(View.GONE);
        findViewById(R.id.my_recycler_view3).setVisibility(View.GONE);

        Intent intent = getIntent();
        String sess_id = "";
        if(!intent.getStringExtra("session_id").isEmpty()) {
            sess_id = intent.getStringExtra("session_id");

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            final DocumentReference reff = db.collection("sessions").document(sess_id);

            reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Session sess = documentSnapshot.toObject(Session.class);

                    ArrayList<Question> qs = sess.getQuestions();
                    setQuestions(qs);
                }
            });
        }

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setQuestions(ArrayList<Question> qs) {
        TextView tv_first = ((TextView) findViewById(R.id.elsokerdes));
        tv_first.setText(qs.get(0).getContent());
    }

}
