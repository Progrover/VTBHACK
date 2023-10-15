package com.platovco.vtb.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.platovco.vtb.R;
import com.platovco.vtb.fragments.pointlist.PointListFragment;
import com.platovco.vtb.models.Branch;
import com.platovco.vtb.models.MarkBranch;

import java.util.ArrayList;

import per.wsj.library.AndRatingBar;


public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.ViewHolder> {
    private ArrayList<Branch> marks;
    Context context;
    PointListFragment fragment;

    LayoutInflater inflater;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.marks_item, parent, false);
        return new MarksAdapter.ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Branch mark = marks.get(position);
        holder.adress.setText(mark.getAddress());
        holder.rating.setRating(Float.parseFloat(String.valueOf(mark.getGrade())));
        holder.load.setText(String.valueOf(mark.getLoad()));
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("mark", mark);
            fragment.getParentFragmentManager().setFragmentResult("markKey", bundle);
            Navigation.findNavController(v).navigate(R.id.map_graph);
        });
    }

    public MarksAdapter(ArrayList<Branch> actions, PointListFragment fragment) {
        this.inflater = LayoutInflater.from(fragment.getContext());
        this.context = fragment.getContext();
        this.fragment = fragment;
        this.marks = actions;
    }


    @Override
    public int getItemCount() {
        return marks == null ? 0 : marks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView markImage;
        final TextView load;
        final TextView adress;
        final TextView distance;
        final AndRatingBar rating;


        public ViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            markImage = itemView.findViewById(R.id.markImageTV);
            load = itemView.findViewById(R.id.loadTV);
            adress = itemView.findViewById(R.id.adressTV);
            distance = itemView.findViewById(R.id.distanceTV);
            rating = itemView.findViewById(R.id.ratingBar);
        }
    }

}

