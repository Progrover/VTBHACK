package com.platovco.vtb.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.platovco.vtb.R;
import com.platovco.vtb.models.MarkBranch;

import java.util.ArrayList;



public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.ViewHolder> {
    private ArrayList<MarkBranch> marks;
    Context context;
    LayoutInflater inflater;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.marks_item, parent, false);
        return new MarksAdapter.ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MarkBranch mark = marks.get(position);


    }

    public MarksAdapter(ArrayList<MarkBranch> actions, LayoutInflater inflater) {
        this.marks = actions;
        this.inflater = inflater;
    }


    @Override
    public int getItemCount() {
        return marks == null ? 0 : marks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView markImage;
        final TextView mark;
        final TextView adress;
        final TextView distance;
        final RatingBar rating;
        final TextView moreInfo;


        public ViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            markImage = itemView.findViewById(R.id.markImageTV);
            mark = itemView.findViewById(R.id.markTV);
            adress = itemView.findViewById(R.id.adressTV);
            distance = itemView.findViewById(R.id.distanceTV);
            rating = itemView.findViewById(R.id.ratingBar);
            moreInfo =  itemView.findViewById(R.id.moreTV);


        }
    }

}

