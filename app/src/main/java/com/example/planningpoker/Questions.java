package com.example.planningpoker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planningpoker.model.Answer;
import com.example.planningpoker.model.Question;
import com.example.planningpoker.model.Session;
import com.example.planningpoker.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.Random;

public class Questions extends AppCompatActivity {


    RecyclerViewAdapter adapter;
    Button sendButton;
    ArrayList<Question> questions = new ArrayList<>();
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        final String sess_id = intent.getStringExtra("session_id");

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        user = (User)intent.getSerializableExtra("user");

        if(!intent.getStringExtra("session_id").isEmpty()) {

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
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.shared_key),
                        Context.MODE_PRIVATE);
                final String vote = sharedPreferences.getString("ANSWER", "");
                if(vote.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please select an answer!", Toast.LENGTH_LONG);
                    return;
                }
                else {
                    final DocumentReference reff2 = db.collection("sessions").document(sess_id);

                    db.runTransaction(new Transaction.Function<Void>() {
                        public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                            DocumentSnapshot snapshot = transaction.get(reff2);

                            Session sess = snapshot.toObject(Session.class);

                            Question q = questions.get(0);
                            for(User u: sess.getUsers()) {
                                Answer a = new Answer();
                                if(u.getName().equals(user.getName())) {
                                    a.setUser(user);
                                    a.setContent(vote);
                                }
                                else{
                                    a.setUser(u);
                                    a.setContent(String.valueOf((new Random()).nextInt(9) + 1));
                                }
                                q.addAnswer(a);
                            }

                            questions.set(0, q);
                            transaction.update(reff2, "questions", questions);
                            return null;
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Questions.this, "Saved successfully!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), ActivityResult.class);
                            intent.putExtra("session_id", sess_id);
                            intent.putExtra("user_name", user.getName());
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to save answer!" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    public void setQuestions(ArrayList<Question> qs) {
        TextView tv_first = ((TextView) findViewById(R.id.elsokerdes));
        tv_first.setText(qs.get(0).getContent());
        questions.add(qs.get(0));
    }

}
