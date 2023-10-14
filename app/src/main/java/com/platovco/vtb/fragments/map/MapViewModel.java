package com.platovco.vtb.fragments.map;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platovco.vtb.models.Mark;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

import java.util.ArrayList;
import java.util.Objects;
//

public class MapViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Mark>> marks = new MutableLiveData<>();
    public MutableLiveData<Boolean> visibilityOfLoadingLD = new MutableLiveData<>(false);
    public MutableLiveData<Bundle> filters = new MutableLiveData<>();
    public MutableLiveData<CameraPosition> cameraPositionLD = new MutableLiveData<>();

    public void getMarksInZone(Point topLeftPoint, Point bottomRightPoint){
        ArrayList<Mark> marks1 = new ArrayList<>();
        marks1.add(new Mark(55.791475059622925, 37.560590339677766, "1"));
        marks1.add(new Mark(55.67457506906592, 37.760233999999976, "2"));
        marks1.add(new Mark(55.73736122517432, 37.6179724958831, "3"));
        Objects.requireNonNull(marks.getValue()).clear();
        marks.postValue(marks1);

    }

}