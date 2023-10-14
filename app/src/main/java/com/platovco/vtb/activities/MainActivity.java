package com.platovco.vtb.activities;
import static com.platovco.vtb.R.id.host_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.platovco.vtb.R;
import com.platovco.vtb.fragments.map.MapFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));

    }
    public void onBackPressed() {
        Navigation.findNavController(this, host_fragment).navigateUp();
    }
}