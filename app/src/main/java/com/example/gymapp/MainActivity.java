package com.example.gymapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btnPlans,btnAllActivities,btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        Utils.initTrainings();

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("About").setMessage("Visit My Git Page for the Code")
                        .setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this,WebActivity.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
            }
        });
        btnAllActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AllTrainingsActivity.class);
                startActivity(intent);
            }
        });

        btnPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PlanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        btnAbout = findViewById(R.id.btnAbout);
        btnAllActivities = findViewById(R.id.btnAllActivities);
        btnPlans = findViewById(R.id.btnAllPlans);
    }
}