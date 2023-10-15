package com.platovco.vtb.fragments.branch;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.platovco.vtb.R;

import per.wsj.library.AndRatingBar;


public class BranchFragment extends Fragment {

    private BranchViewModel mViewModel;

    public static BranchFragment newInstance() {
        return new BranchFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView branchPhotoIV = view.findViewById(R.id.branchPhotoIV);
        TextView adressTV = view.findViewById(R.id.adressTV);
        AndRatingBar ratingBar = view.findViewById(R.id.ratingBar);
        TextView ratingNumTV = view.findViewById(R.id.ratingNumTV);
        TextView marksCountTV = view.findViewById(R.id.marksCountTV);
        TextView minRatingMarkTV = view.findViewById(R.id.minRatingMarkTV);
        TextView maxRatingMarkTV = view.findViewById(R.id.maxRatingMarkTV);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_branch, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BranchViewModel.class);
        // TODO: Use the ViewModel
    }

}