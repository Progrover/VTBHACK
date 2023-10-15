package com.platovco.vtb.fragments.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.platovco.vtb.R;


public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private View rootView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        BottomNavigationView mainBNV = rootView.findViewById(R.id.mainBNV);
        NavHostFragment host = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.host_fragment);
        NavController navController = host.getNavController();
        NavigationUI.setupWithNavController(mainBNV, navController);
        mainBNV.setOnItemReselectedListener(item -> {
            if (item.getItemId() == R.id.map_graph) {
                navController.popBackStack(R.id.mapFragment, false);
                return;
            }
            if (item.getItemId() == R.id.recomendations_graph) {
                navController.popBackStack(R.id.pointListFragment, false);
                return;
            }
        });
    }
}