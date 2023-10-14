package com.platovco.vtb.fragments.map;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platovco.vtb.models.Mark;
import com.yandex.mapkit.map.CameraPosition;

import java.util.ArrayList;
//

public class MapViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Mark>> marks = new MutableLiveData<>();
    public MutableLiveData<Boolean> visibilityOfLoadingLD = new MutableLiveData<>(false);
    public MutableLiveData<Bundle> filters = new MutableLiveData<>();
    public MutableLiveData<CameraPosition> cameraPositionLD = new MutableLiveData<>();
}