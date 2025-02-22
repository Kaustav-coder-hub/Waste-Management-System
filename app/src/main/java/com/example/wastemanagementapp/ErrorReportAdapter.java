package com.example.wastemanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ErrorReportAdapter extends RecyclerView.Adapter<ErrorReportAdapter.ViewHolder> {

    private final List<String> errorList;

    public ErrorReportAdapter(List<String> errorList) {
        this.errorList = errorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error_report, parent, false);

        // Ensure proper layout parameters
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(4, 4, 4, 4); // Small margin
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.errorText.setText(errorList.get(position));
    }

    @Override
    public int getItemCount() {
        return errorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView errorText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            errorText = itemView.findViewById(R.id.tvErrorText);
        }
    }
}
