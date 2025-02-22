package com.example.wastemanagementapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.List;

public class Waste_Sorting_Data extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waste_sorting_data);

        // Initialize PieChart
        pieChart = findViewById(R.id.pieChartWaste);
        setupPieChart();

        // Initialize RecyclerView
        RecyclerView recyclerViewErrors = findViewById(R.id.recyclerErrorReports);
        recyclerViewErrors.setLayoutManager(new LinearLayoutManager(this));

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
        dataSet.setColors(new int[]{R.color.blue, R.color.green, R.color.yellow}, this);

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // Refresh chart
    }
}
