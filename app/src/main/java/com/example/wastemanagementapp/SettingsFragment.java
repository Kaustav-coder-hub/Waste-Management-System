package com.example.wastemanagementapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.fragment.app.Fragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsFragment extends Fragment {

    private DatabaseReference bin1Ref, bin2Ref, bin3Ref, lidRef;
    private Button rotateBin1, rotateBin2, rotateBin3;
    private SeekBar thresholdBin1, thresholdBin2, thresholdBin3;
    private Switch switchLid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        initializeFirebase();
        initializeViews(view);
        setupButtonListeners();
        setupSeekBarListeners();
        setupSwitchListener();

        return view;
    }

    // Initialize Firebase Database references
    private void initializeFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        bin1Ref = database.getReference("bins/bin1");
        bin2Ref = database.getReference("bins/bin2");
        bin3Ref = database.getReference("bins/bin3");
        lidRef = database.getReference("lid");
    }

    // Initialize UI components
    private void initializeViews(View view) {
        rotateBin1 = view.findViewById(R.id.btnRotateBin1);
        rotateBin2 = view.findViewById(R.id.btnRotateBin2);
        rotateBin3 = view.findViewById(R.id.btnRotateBin3);

        thresholdBin1 = view.findViewById(R.id.seekBarThreshold1);
        thresholdBin2 = view.findViewById(R.id.seekBarThreshold2);
        thresholdBin3 = view.findViewById(R.id.seekBarThreshold3);

        switchLid = view.findViewById(R.id.switchLid);
    }

    // Set up button click listeners
    private void setupButtonListeners() {
        rotateBin1.setOnClickListener(v -> {
            bin1Ref.child("rotate").setValue(true);
            bin2Ref.child("rotate").setValue(false);
            bin3Ref.child("rotate").setValue(false);
        });

        rotateBin2.setOnClickListener(v -> {
            bin1Ref.child("rotate").setValue(false);
            bin2Ref.child("rotate").setValue(true);
            bin3Ref.child("rotate").setValue(false);
        });

        rotateBin3.setOnClickListener(v -> {
            bin1Ref.child("rotate").setValue(false);
            bin2Ref.child("rotate").setValue(false);
            bin3Ref.child("rotate").setValue(true);
        });
    }

    // Set up SeekBar change listeners
    private void setupSeekBarListeners() {
        setSeekBarListener(thresholdBin1, bin1Ref);
        setSeekBarListener(thresholdBin2, bin2Ref);
        setSeekBarListener(thresholdBin3, bin3Ref);
    }

    // Helper method to handle SeekBar changes
    private void setSeekBarListener(SeekBar seekBar, DatabaseReference ref) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ref.child("threshold").setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    // Set up switch listener for lid control
    private void setupSwitchListener() {
        switchLid.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Switch is on the right side (Open)
                lidRef.child("open").setValue(true);
            } else {
                // Switch is on the left side (Close)
                lidRef.child("open").setValue(false);
            }
        });
    }
}