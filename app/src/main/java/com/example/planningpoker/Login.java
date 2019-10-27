package com.example.planningpoker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planningpoker.model.Session;
import com.example.planningpoker.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        final EditText et_name = (EditText)findViewById(R.id.et_name);
        final EditText et_code =  (EditText)findViewById(R.id.et_code);
        Button bt_login = (Button)findViewById(R.id.b_login);
        Button bt_image = (Button)findViewById(R.id.b_imageUp);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Login.this,Questions.class);
//
//
//
//                startActivity(intent);
            final DocumentReference reff = db.collection("sessions").document(et_code.getText().toString());

            db.runTransaction(new Transaction.Function<Void>() {
                public  Void apply(Transaction transaction) throws FirebaseFirestoreException {
                    DocumentSnapshot snapshot = transaction.get(reff);

                    if(!snapshot.exists()) {
                        return null;
                    }
                    Session sess = snapshot.toObject(Session.class);
                    ArrayList<User> users = sess.getUsers();
                    User user = new User(et_name.getText().toString());
                    users.add(user);
                    transaction.update(reff, "users", users);
                    return null;
                }
            }).addOnSuccessListener(
                    new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(Login.this,Questions.class);
                            startActivity(intent);
                        }
                    }
            ).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed spectacularly! " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            }
        });
        bt_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
