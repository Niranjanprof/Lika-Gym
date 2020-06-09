package com.example.gymapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder>{

    public interface  RemovePlan{
        void onRemovePlan(Plan plan);
    }

    ArrayList<Plan> plans = new ArrayList<>();
    private Context context;
    private String type = "";
    private RemovePlan removePlan;

    public PlanAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO context ----> parent.getContext()
        View view = LayoutInflater.from(context).inflate(R.layout.plan_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(plans.get(position).getTraining().getName());
        holder.desc.setText(plans.get(position).getTraining().getShortDesc());
        holder.time.setText(String.valueOf(plans.get(position).getMinutes() + " min"));
        Glide.with(context).asBitmap().load(plans.get(position).getTraining().getImageUrl()).into(holder.image);
        if(plans.get(position).isFinished()){
            holder.empty.setVisibility(View.GONE);
            holder.checked.setVisibility(View.VISIBLE);
        }else{

            holder.checked.setVisibility(View.GONE);
            holder.empty.setVisibility(View.VISIBLE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TrainingActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(TRAINING_KEY,plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });

        if(type.equals("edit")){
            holder.empty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context)
                            .setTitle("Finished").setMessage("Have you finished "+plans.get(position).getTraining().getName()+" ?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (Plan p:Utils.getPlans()
                                         ) {
                                        if(p.equals(plans.get(position))){
                                            p.setFinished(true);
                                        }
                                    }
                                    notifyDataSetChanged();
                                }
                            });
                    builder.create().show();
                }
            });
        }
        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context)
                        .setTitle("Remove")
                        .setMessage("Are you sure you want to remove "+plans.get(position).getTraining().getName()+" from your plan?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    removePlan = (RemovePlan) context;
                                    removePlan.onRemovePlan(plans.get(position));
                                }catch (ClassCastException e){
                                    e.printStackTrace();
                                }
                            }
                        });

                builder.create().show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private MaterialCardView parent;
        private TextView name,time,desc;
        private ImageView image,empty,checked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            name = itemView.findViewById(R.id.trainingName);
            time = itemView.findViewById(R.id.time);
            desc = itemView.findViewById(R.id.Desc);
            image = itemView.findViewById(R.id.image);
            empty = itemView.findViewById(R.id.emptyCircle);
            checked = itemView.findViewById(R.id.checkedCircle);
        }
    }
}
