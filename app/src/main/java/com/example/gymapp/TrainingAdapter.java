package com.example.gymapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static com.example.gymapp.TrainingActivity.TRAINING_KEY;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder>{

    private static final String TAG = "TrainingAdapter";
    private ArrayList<Training> trainings = new ArrayList<>();
    private Context context;

    public TrainingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TrainingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingAdapter.ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: Called");
        holder.activityName.setText(trainings.get(position).getName());
        holder.shortDescription.setText(trainings.get(position).getShortDesc());
        Glide.with(context)
                .asBitmap()
                .load(trainings.get(position).getImageUrl())
                .into(holder.imageView);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TrainingActivity.class);
                intent.putExtra(TRAINING_KEY,trainings.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private MaterialCardView parent;
        private ImageView imageView;
        private TextView activityName,shortDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imageView = itemView.findViewById(R.id.image);
            activityName = itemView.findViewById(R.id.activityName);
            shortDescription = itemView.findViewById(R.id.shortDesc);
        }
    }
}
