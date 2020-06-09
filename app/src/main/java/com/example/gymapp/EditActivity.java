package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements PlanAdapter.RemovePlan {

    private TextView txtDay;
    private RecyclerView recyclerView;
    private Button btnAddPlan;
    private PlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initViews();

        adapter = new PlanAdapter(this);
        adapter.setType("edit");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if(null != intent){
            String day = intent.getStringExtra("day");
            if(null != day){
                txtDay.setText(day);
                ArrayList<Plan> plans = getPlansByDay(day);
                adapter.setPlans(plans);
            }
        }
        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPlanIntent = new Intent(EditActivity.this,AllTrainingsActivity.class);
                startActivity(addPlanIntent);
            }
        });
    }

    private ArrayList<Plan> getPlansByDay(String day){
        ArrayList<Plan> allPlans = Utils.getPlans();
        ArrayList<Plan> plans = new ArrayList<>();
        for(Plan p: allPlans){
            if(p.getDay().equalsIgnoreCase(day)){
                plans.add(p);
            }
        }
        return plans;
    }

    private void initViews() {
        txtDay = findViewById(R.id.txtDay);
        recyclerView = findViewById(R.id.recyclerView);
        btnAddPlan= findViewById(R.id.btnMorePlan);
    }

    @Override
    public void onRemovePlan(Plan plan) {
        if(Utils.removePlan(plan)){
            Toast.makeText(this, "Plan Removed", Toast.LENGTH_SHORT).show();
            adapter.setPlans(getPlansByDay(plan.getDay()));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}