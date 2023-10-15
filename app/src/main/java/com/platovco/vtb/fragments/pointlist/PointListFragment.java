package com.platovco.vtb.fragments.pointlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.angcyo.tablayout.DslTabLayout;
import com.platovco.vtb.R;
import com.platovco.vtb.adapters.MarksAdapter;

public class PointListFragment extends Fragment {

    private PointListViewModel mViewModel;
    private LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    private MarksAdapter adapter;

    public static PointListFragment newInstance() {
        return new PointListFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DslTabLayout switcher = view.findViewById(R.id.switcher);
        RecyclerView recyclerView = view.findViewById(R.id.marksRV);
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