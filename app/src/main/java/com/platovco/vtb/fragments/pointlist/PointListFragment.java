package com.platovco.vtb.fragments.pointlist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platovco.vtb.R;

public class PointListFragment extends Fragment {

    private PointListViewModel mViewModel;

    public static PointListFragment newInstance() {
        return new PointListFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // TextView textView = view.findViewById(R.id.text);
       // textView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_pointListFragment_to_branchFragment));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_point_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PointListViewModel.class);
        // TODO: Use the ViewModel
    }

}