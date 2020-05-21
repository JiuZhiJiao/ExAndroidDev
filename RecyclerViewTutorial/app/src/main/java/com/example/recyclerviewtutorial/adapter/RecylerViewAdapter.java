package com.example.recyclerviewtutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtutorial.R;
import com.example.recyclerviewtutorial.model.CourseResult;

import java.util.List;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView unitTextView;
        public TextView markTextView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            unitTextView = itemView.findViewById(R.id.unit_name);
            markTextView = itemView.findViewById(R.id.mark);
            imageView = itemView.findViewById(R.id.iv_item_delete);
        }
    }

    private List<CourseResult> courseResults;

    public RecylerViewAdapter(List<CourseResult> units) {
        courseResults = units;
    }

    public void addUnits(List<CourseResult> units) {
        courseResults = units;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View unitsView = inflater.inflate(R.layout.rv_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(unitsView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CourseResult unit = courseResults.get(position);

        TextView tvUnit = holder.unitTextView;
        tvUnit.setText(unit.getUnit());
        TextView tvMark = holder.markTextView;
        tvMark.setText((Integer.toString(unit.getMark())));
        ImageView imageView = holder.imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseResults.remove(unit);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseResults.size();
    }
}
