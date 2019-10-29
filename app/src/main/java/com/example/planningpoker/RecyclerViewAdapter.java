package com.example.planningpoker;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private String[] mData;
    private LayoutInflater mInflater;
    private Context context;


    RecyclerViewAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setClickable(true);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Item is clicked" , Toast.LENGTH_SHORT).show();
//                SharedPreferences sharedPrefs = context.getPre
//                SharedPreferences.Editor editor = sharedPrefs.edit();
//                editor.putString("ANSWER", ((TextView) v).getText().toString());
//                editor.commit();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.card.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView card;

        public ViewHolder(View view) {
            super(view);
            card = view.findViewById(R.id.card);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "szoveg" + ((TextView)v).getText().toString(), Toast.LENGTH_LONG).show();
                    ((TextView)v).setBackgroundColor(v.getContext().getResources().getColor(R.color.colorAccent));
                    ((TextView)v).setTextColor(v.getContext().getResources().getColor(R.color.white));

                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(v.getContext().getResources().getString(R.string.shared_key),
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("ANSWER", ((TextView)v).getText().toString());
                    editor.commit();
                }
            });

        }

    }
}
