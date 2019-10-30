package com.example.planningpoker;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpoker.model.Answer;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private Context context;
    private ArrayList<Answer> answers;

    public ResultAdapter(Context context, ArrayList<Answer> answers) {
        this.context = context;
        this.answers = answers;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_result_row, parent, false);
        return new ResultViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.tv_answer.setText(answers.get(position).getContent());
        holder.tv_name.setText(answers.get(position).getUser().getName());
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }
}

class ResultViewHolder extends RecyclerView.ViewHolder {
    TextView tv_answer;
    TextView tv_name;

    public ResultViewHolder(View view) {
        super(view);

        tv_answer = view.findViewById(R.id.tv_number);
        tv_name = view.findViewById(R.id.tv_name);
    }
}
