package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText et_name = (EditText)findViewById(R.id.et_name);
        EditText et_code =  (EditText)findViewById(R.id.et_code);
        Button bt_login = (Button)findViewById(R.id.b_login);
        Button bt_image = (Button)findViewById(R.id.b_imageUp);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Questions.class);
                startActivity(intent);
            }
        });
        bt_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
