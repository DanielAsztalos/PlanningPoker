package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.planningpoker.model.Question;
import com.example.planningpoker.model.Session;
import com.example.planningpoker.model.User;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b_login = (Button)findViewById(R.id.b_login);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        });

        initializeSession();

    }

    public void initializeSession() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Session session = new Session();

        session.setKey(123456);

        Question q = new Question();
        q.setContent("Adatbázis megtervezése");
        Question q2 = new Question();
        q2.setContent("UI tervezés");
        Question q3 = new Question();
        q3.setContent("Home Activity implementálása");

        User u1 = new User("Teszt1");
        User u2 = new User("Teszt2");
        User u3 = new User("Teszt3");

        session.addUser(u1);
        session.addUser(u2);
        session.addUser(u3);

        session.addQuestion(q);
        session.addQuestion(q2);
        session.addQuestion(q3);

        db.collection("sessions").document(String.valueOf(123456)).set(session);
    }
}
