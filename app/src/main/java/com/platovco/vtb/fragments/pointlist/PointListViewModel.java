package com.platovco.vtb.fragments.pointlist;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platovco.vtb.models.Branch;
import com.platovco.vtb.models.MarkBranch;
import com.platovco.vtb.web.ConnectionService;
import com.platovco.vtb.web.JsonService;
import com.platovco.vtb.web.httpData.branch.BranchFindInRangeResponse;
import com.platovco.vtb.web.httpData.branch.BranchFindOptimalRequest;
import com.platovco.vtb.web.httpData.branch.BranchFindOptimalResponse;
import com.yandex.mapkit.geometry.Point;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;

public class PointListViewModel extends ViewModel implements ConnectionService {
    public MutableLiveData<List<Branch>> branches = new MutableLiveData<>();

    public void getOptimalBranches(Point me) {
        var requestData = new BranchFindOptimalRequest(
                me.getLatitude(),
                me.getLongitude());
        var request = buildRequest("branch/find-optimal", requestData);
        runAsync(() -> sendRequest(request, this::onOptimalBranches));

    }

    private void onOptimalBranches(int code, ResponseBody responseBody) throws IOException {
        if (code != 200) {
            return;
        }
        var response = JsonService.fromJson(responseBody.string(), BranchFindOptimalResponse.class);
        branches.postValue(response.getBranches());
    }

    @Override
    public void onMessage(String message) {
        Log.i("conn: ", message);
    }
}