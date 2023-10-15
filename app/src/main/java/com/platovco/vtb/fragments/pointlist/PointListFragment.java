package com.platovco.vtb.fragments.pointlist;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.angcyo.tablayout.DslTabLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.LocationServices;
import com.platovco.vtb.R;
import com.platovco.vtb.adapters.MarksAdapter;
import com.platovco.vtb.fragments.map.MapViewModel;
import com.platovco.vtb.models.Branch;
import com.platovco.vtb.models.CustomPoint;
import com.platovco.vtb.models.MarkBranch;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

import java.util.ArrayList;
import java.util.List;

public class PointListFragment extends Fragment {

    private PointListViewModel mViewModel;
    private LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    private MarksAdapter adapter;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private com.huawei.hms.location.FusedLocationProviderClient fusedLocationClientHuawei;
    DslTabLayout switcher;
    ArrayList<Branch> markBranches = new ArrayList<>();
    RecyclerView recyclerView;
    private View rootView;


    public static PointListFragment newInstance() {
        return new PointListFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        observe();
        bringOptimalData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PointListViewModel.class);
        return rootView = inflater.inflate(R.layout.fragment_point_list, container, false);

    }

    private void init() {
        switcher = rootView.findViewById(R.id.switcher);
        recyclerView = rootView.findViewById(R.id.marksRV);
        adapter = new MarksAdapter(markBranches, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                result -> {
                    if (result) {
                        bringOptimalData();
                    } else {
                        Toast.makeText(getContext(), "Дайте доступ к геолокации", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        fusedLocationClientHuawei = com.huawei.hms.location.LocationServices.getFusedLocationProviderClient(requireContext());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void observe(){
        mViewModel.branches.observe(getViewLifecycleOwner(), branches -> {
            markBranches.clear();
            markBranches.addAll(branches);
            adapter.notifyDataSetChanged();
        });
    }

    private void bringOptimalData() {
        if (ActivityCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isGPSEnabled()) {
                if (areGoogleServicesAvailable()) {
                    LocationServices.getFusedLocationProviderClient(requireContext()).getLastLocation()
                            .addOnSuccessListener(location -> {
                                if (location != null) {
                                    mViewModel.getOptimalBranches(new Point(location.getLatitude(), location.getLongitude()));
                                } else {
                                    Toast.makeText(requireContext(), "Ошибка определения местоположения", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(requireContext(), "Ошибка определения местоположения", Toast.LENGTH_SHORT).show();

                            });
                } else {
                    fusedLocationClientHuawei.getLastLocation()
                            .addOnSuccessListener(location -> {
                                if (location != null) {
                                    mViewModel.getOptimalBranches(new Point(location.getLatitude(), location.getLongitude()));
                                } else {
                                    Toast.makeText(requireContext(), "Ошибка определения местоположения", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(requireContext(), "Ошибка определения местоположения", Toast.LENGTH_SHORT).show();
                            });
                }
            } else {
                Toast.makeText(getContext(), "Включите геолокацию для лучшей работы приложения", Toast.LENGTH_SHORT).show();
            }

        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private boolean areGoogleServicesAvailable() {
        int availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireContext());
        return availability == ConnectionResult.SUCCESS;

    }

    private boolean isGPSEnabled() {
        LocationManager locationManager;
        boolean isEnabled;
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }



}