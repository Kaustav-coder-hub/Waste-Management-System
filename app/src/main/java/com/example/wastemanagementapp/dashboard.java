package com.example.wastemanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.wastemanagementapp.R;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class dashboard extends AppCompatActivity{

    private ProgressBar progressDryWaste, progressWetWaste, progressMetalicWaste;
    private int currentDryProgress = 0, currentWetProgress = 0, currentMetalicProgress = 0;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.dashboard); // Fixed layout reference

        // Initialize progress bars
        progressDryWaste = findViewById(R.id.progressDryWaste);
        progressWetWaste = findViewById(R.id.progressWetWaste);
        progressMetalicWaste = findViewById(R.id.progressMetalWaste); // Fixed ID reference

        // Initialize buttons
        Button updateDryProgressButton = findViewById(R.id.updateDryProgressButton);
        Button updateWetProgressButton = findViewById(R.id.updateWetProgressButton);
        Button updateMetalicProgressButton = findViewById(R.id.updateMetallicProgressButton);

        TextView move = findViewById(R.id.move);
        move.setOnClickListener(v -> {
            Intent intent = new Intent(dashboard.this, Waste_Sorting_Data.class);
            startActivity(intent);
        });

        // Set initial progress values
        progressDryWaste.setProgress(currentDryProgress);
        progressWetWaste.setProgress(currentWetProgress);
        progressMetalicWaste.setProgress(currentMetalicProgress);

        // Set click listeners
        updateDryProgressButton.setOnClickListener(v -> updateProgress("dry"));
        updateWetProgressButton.setOnClickListener(v -> updateProgress("wet"));
        updateMetalicProgressButton.setOnClickListener(v -> updateProgress("metal"));
    }

    private void updateProgress(String type) {
        switch (type) {
            case "dry":
                currentDryProgress += 10;
                if (currentDryProgress > 100) currentDryProgress = 100;
                progressDryWaste.setProgress(currentDryProgress);
                break;
            case "wet":
                currentWetProgress += 10;
                if (currentWetProgress > 100) currentWetProgress = 100;
                progressWetWaste.setProgress(currentWetProgress);
                break;
            case "metal":
                currentMetalicProgress += 10;
                if (currentMetalicProgress > 100) currentMetalicProgress = 100;
                progressMetalicWaste.setProgress(currentMetalicProgress);
                break;
        }

        checkProgressThreshold();
    }

    private void checkProgressThreshold() {
        if (currentDryProgress >= 90 || currentWetProgress >= 90 || currentMetalicProgress >= 90) {
            showProgressAlert();
        }
    }

    private void showProgressAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Waste Bin Alert");
        builder.setMessage("One or more bins are over 90% full! Please take action.");
        builder.setCancelable(true); // Allows dismissing dialog by clicking outside
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
