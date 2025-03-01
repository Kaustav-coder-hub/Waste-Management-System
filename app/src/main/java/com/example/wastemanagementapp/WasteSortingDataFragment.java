package com.example.wastemanagementapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class WasteSortingDataFragment extends Fragment {

    private PieChart pieChart;
    private RecyclerView recyclerViewErrors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.waste_sorting_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize PieChart
        pieChart = view.findViewById(R.id.pieChartWaste);
        setupPieChart();

        // Initialize RecyclerView
        recyclerViewErrors = view.findViewById(R.id.recyclerErrorReports);
        recyclerViewErrors.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Load error reports
        List<String> errors = new ArrayList<>();
        errors.add("Sensor Failure - Wet Bin");
        errors.add("Jammed Lid - Dry Bin");
        errors.add("Network Issue - Metallic Bin");

        ErrorReportAdapter adapter = new ErrorReportAdapter(errors);
        recyclerViewErrors.setAdapter(adapter);
    }

    private void setupPieChart() {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(50f, "Dry Waste"));
        entries.add(new PieEntry(30f, "Wet Waste"));
        entries.add(new PieEntry(20f, "Metallic Waste"));

        PieDataSet dataSet = new PieDataSet(entries, "Waste Types");
        dataSet.setColors(new int[]{R.color.blue, R.color.green, R.color.yellow}, requireContext());

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // Refresh chart
    }
}
