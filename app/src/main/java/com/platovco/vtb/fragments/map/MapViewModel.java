package com.platovco.vtb.fragments.map;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platovco.vtb.models.ActualRoute;
import com.platovco.vtb.models.Mark;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

import java.util.ArrayList;
import java.util.Objects;
//

public class MapViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Mark>> marks = new MutableLiveData<>();
    public MutableLiveData<Boolean> visibilityOfLoadingLD = new MutableLiveData<>(false);
    public MutableLiveData<ActualRoute> actualRouteLD = new MutableLiveData<>();
    public MutableLiveData<Point> destinationPointLD = new MutableLiveData<>();

    public MutableLiveData<CameraPosition> cameraPositionLD = new MutableLiveData<>();

    public void getMarksInZone(Point topLeftPoint, Point bottomRightPoint) {
        ArrayList<Mark> marks1 = new ArrayList<>();
        marks1.add(new Mark(37.560590339677766, 55.791475059622925, "1"));
        marks1.add(new Mark(37.760233999999976, 55.67457506906592, "2"));
        marks1.add(new Mark(37.6179724958831, 55.73736122517432, "3"));
        Objects.requireNonNull(marks.getValue()).clear();
        marks.postValue(marks1);
    }
}