package com.example.wastemanagementapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    private ProgressBar progressDryWaste, progressWetWaste, progressMetalicWaste;
    private TextView tvDryWasteStatus, tvWetWasteStatus, tvMetalWasteStatus;
    private int currentDryProgress = 50, currentWetProgress = 0, currentMetalicProgress = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ProgressBars
        progressDryWaste = view.findViewById(R.id.progressDryWaste);
        progressWetWaste = view.findViewById(R.id.progressWetWaste);
        progressMetalicWaste = view.findViewById(R.id.progressMetalWaste);

        // Initialize TextViews for bin status
        tvDryWasteStatus = view.findViewById(R.id.tvDryWasteStatus);
        tvWetWasteStatus = view.findViewById(R.id.tvWetWasteStatus);
        tvMetalWasteStatus = view.findViewById(R.id.tvMetalWasteStatus);

        // Move to Waste Sorting Data Activity
        TextView move = view.findViewById(R.id.move);
        move.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), WasteSortingDataFragment.class);
            startActivity(intent);
        });

        // Set initial progress values
        updateBinStatus(progressDryWaste, tvDryWasteStatus, currentDryProgress);
        updateBinStatus(progressWetWaste, tvWetWasteStatus, currentWetProgress);
        updateBinStatus(progressMetalicWaste, tvMetalWasteStatus, currentMetalicProgress);
    }

    // Function to change progress bar color & update text
    private void updateBinStatus(ProgressBar progressBar, TextView textView, int percentage) {
        progressBar.setProgress(percentage);
        textView.setText(percentage + "% Full");

        // Change ProgressBar color based on percentage
        if (percentage <= 50) {
            progressBar.getProgressDrawable().setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark), PorterDuff.Mode.SRC_IN);
            textView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_dark));
        } else if (percentage <= 80) {
            progressBar.getProgressDrawable().setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark), PorterDuff.Mode.SRC_IN);
            textView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark));
        } else {
            progressBar.getProgressDrawable().setColorFilter(
                    ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark), PorterDuff.Mode.SRC_IN);
            textView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark));
        }
    }
}
