package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
