package com.platovco.vtb.providers;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;

public class GoogleFusedLocationProvider implements FusedLocationProviderClient {
    @NonNull
    @Override
    public Task<Void> flushLocations() {
        return null;
    }

    @NonNull
    @Override
    public Task<Location> getCurrentLocation(int i, @Nullable CancellationToken cancellationToken) {
        return null;
    }

    @NonNull
    @Override
    public Task<Location> getCurrentLocation(@NonNull CurrentLocationRequest currentLocationRequest, @Nullable CancellationToken cancellationToken) {
        return null;
    }

    @NonNull
    @Override
    public Task<Location> getLastLocation() {
        return null;
    }

    @NonNull
    @Override
    public Task<Location> getLastLocation(@NonNull LastLocationRequest lastLocationRequest) {
        return null;
    }

    @NonNull
    @Override
    public Task<LocationAvailability> getLocationAvailability() {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> removeLocationUpdates(@NonNull PendingIntent pendingIntent) {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> removeLocationUpdates(@NonNull LocationCallback locationCallback) {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> removeLocationUpdates(@NonNull LocationListener locationListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> requestLocationUpdates(@NonNull LocationRequest locationRequest, @NonNull PendingIntent pendingIntent) {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> requestLocationUpdates(@NonNull LocationRequest locationRequest, @NonNull LocationCallback locationCallback, @Nullable Looper looper) {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> requestLocationUpdates(@NonNull LocationRequest locationRequest, @NonNull LocationListener locationListener, @Nullable Looper looper) {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> requestLocationUpdates(@NonNull LocationRequest locationRequest, @NonNull Executor executor, @NonNull LocationCallback locationCallback) {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> requestLocationUpdates(@NonNull LocationRequest locationRequest, @NonNull Executor executor, @NonNull LocationListener locationListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> setMockLocation(@NonNull Location location) {
        return null;
    }

    @NonNull
    @Override
    public Task<Void> setMockMode(boolean b) {
        return null;
    }

    @NonNull
    @Override
    public ApiKey<Api.ApiOptions.NoOptions> getApiKey() {
        return null;
    }
}
