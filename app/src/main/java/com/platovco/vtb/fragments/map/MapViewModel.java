package com.platovco.vtb.fragments.map;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platovco.vtb.models.MarkBranch;
import com.platovco.vtb.web.ConnectionService;
import com.platovco.vtb.web.JsonService;
import com.platovco.vtb.web.httpData.branch.BranchFindInRangeRequest;
import com.platovco.vtb.web.httpData.branch.BranchFindInRangeResponse;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
//

public class MapViewModel extends ViewModel implements ConnectionService {
    public MutableLiveData<List<MarkBranch>> marks = new MutableLiveData<>();
    public MutableLiveData<Boolean> visibilityOfLoadingLD = new MutableLiveData<>(false);
    public MutableLiveData<Bundle> filters = new MutableLiveData<>();
    public MutableLiveData<CameraPosition> cameraPositionLD = new MutableLiveData<>();

    public void getMarksInZone(Point topRightPoint, Point bottomLeftPoint) {
        var requestData = new BranchFindInRangeRequest(
                bottomLeftPoint.getLatitude(),
                topRightPoint.getLongitude(),
                topRightPoint.getLatitude(),
                bottomLeftPoint.getLongitude());
        var request = buildRequest("branch/find-in-range", requestData);
        runAsync(() -> sendRequest(request, this::onMarksInZoneReceived));
    }

    private void onMarksInZoneReceived(int code, ResponseBody responseBody) throws IOException {
        if (code != 200) {
            return;
        }
        var response = JsonService.fromJson(responseBody.string(), BranchFindInRangeResponse.class);
        var markBranches = response.getBranches().stream().map(MarkBranch::new).collect(Collectors.toList());
        marks.postValue(markBranches);
    }

    @Override
    public void onMessage(String message) {
        Log.i("request_info: ", message);
    }
}