package com.platovco.vtb.fragments.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.LocationServices;
import com.platovco.vtb.R;
import com.platovco.vtb.models.CustomPoint;
import com.platovco.vtb.models.Mark;
import com.platovco.vtb.providers.GoogleFusedLocationProvider;
import com.platovco.vtb.providers.TextImageProvider;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.RequestPointType;
import com.yandex.mapkit.ScreenPoint;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.directions.driving.DrivingOptions;
import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.directions.driving.DrivingRouter;
import com.yandex.mapkit.directions.driving.DrivingSession;
import com.yandex.mapkit.directions.driving.VehicleOptions;
import com.yandex.mapkit.directions.driving.VehicleType;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.FilteringMode;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateReason;
import com.yandex.mapkit.map.Cluster;
import com.yandex.mapkit.map.ClusterListener;
import com.yandex.mapkit.map.ClusterTapListener;
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.MapWindow;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.Error;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MapFragment extends Fragment implements ClusterListener, MapObjectTapListener, ClusterTapListener, DrivingSession.DrivingRouteListener {

    private MapViewModel mViewModel;
    private View rootView;
    private MapView mapView;
    private ImageView zoomPlusBTN;
    private ImageView zoomMinusBTN;
    private MapWindow mapWindow;
    private MapObjectCollection mapObjects;

    private ImageView locationBTN;
    final CameraListener listener = new CameraListener() {
        @Override
        public void onCameraPositionChanged(@NonNull Map map, @NonNull CameraPosition cameraPosition, @NonNull CameraUpdateReason cameraUpdateReason, boolean finished) {
            mViewModel.cameraPositionLD.setValue(cameraPosition);
            if (finished) {
                mViewModel.getMarksInZone(mapWindow.screenToWorld(new ScreenPoint(0, 0)), mapWindow.screenToWorld(new ScreenPoint(
                        mapWindow.width(), mapWindow.height())));
            }
        }
    };
    private ActivityResultLauncher<String> requestPermissionLauncher;
    public static double startLon = 37.615560;
    public static double startLat = 55.752220;
    public static float startZoom = 10.0f;
    public static float startAnimationDuration = 1;


    ClusterizedPlacemarkCollection clusterizedCollection;
    FragmentManager fragmentManager;
    PlacemarkMapObject userLocationPlacemark;
    LinearLayout loadingLL;
    CustomPoint userLocation;
    private com.huawei.hms.location.FusedLocationProviderClient fusedLocationClientHuawei;

    Dialog dialog;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        mViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        init();
        initListeners();
        initUserLocation();
        moveMap(false);
        mapView.getMap().setRotateGesturesEnabled(false);
        return rootView;

    }

    protected synchronized void moveMap(boolean moveToUserLocation) {
        boolean mapMoved = false;
        Random r = new Random();
        float randomLat = -0.0150f + r.nextFloat() * (0.0150f);
        float randomLon = -0.0150f + r.nextFloat() * (0.0150f);
        startLat += randomLat;
        startLon += randomLon;
        if (!moveToUserLocation) {
            if (getArguments() != null) {
                mapView.getMap().move(
                        new CameraPosition(new Point(getArguments().getDouble("latitude"),
                                getArguments().getDouble("longitude")), 18, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, 0.5F),
                        null);
                mapMoved = true;
                mViewModel.visibilityOfLoadingLD.setValue(false);
            }
            if (mViewModel.cameraPositionLD.getValue() != null) {
                if (!mapMoved) {
                    mapView.getMap().move(mViewModel.cameraPositionLD.getValue());
                    mapMoved = true;
                    mViewModel.visibilityOfLoadingLD.setValue(false);
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isGPSEnabled()) {
                boolean finalMapMoved = mapMoved;
                if (areGoogleServicesAvailable()) {
                    LocationServices.getFusedLocationProviderClient(requireContext()).getLastLocation()
                            .addOnSuccessListener(location -> {
                                if (location != null) {
                                    if (!finalMapMoved) {
                                        mapView.getMap().move(
                                                new CameraPosition(new Point(location.getLatitude(),
                                                        location.getLongitude()), 16, 0.0f, 0.0f),
                                                new Animation(Animation.Type.SMOOTH, startAnimationDuration),
                                                null);
                                    }
                                    loadingLL.setVisibility(View.GONE);
                                    userLocationPlacemark.setGeometry(new Point(location.getLatitude(),
                                            location.getLongitude()));
                                    userLocation = new CustomPoint(location);
                                    userLocationPlacemark.setVisible(true);
                                } else {
                                    mapView.getMap().move(
                                            new CameraPosition(new Point(startLat, startLon),
                                                    startZoom, 0.0f, 0.0f),
                                            new Animation(Animation.Type.SMOOTH, startAnimationDuration),
                                            null);
                                }
                            })
                            .addOnFailureListener(e -> {
                                if (!finalMapMoved) {
                                    mapView.getMap().move(
                                            new CameraPosition(new Point(startLat, startLon),
                                                    startZoom, 0.0f, 0.0f),
                                            new Animation(Animation.Type.SMOOTH, startAnimationDuration),
                                            null);
                                }
                                mViewModel.visibilityOfLoadingLD.setValue(false);
                            });
                } else {
                    fusedLocationClientHuawei.getLastLocation()
                            .addOnSuccessListener(location -> {
                                if (location != null) {
                                    if (!finalMapMoved) {
                                        mapView.getMap().move(
                                                new CameraPosition(new Point(location.getLatitude(),
                                                        location.getLongitude()), 16, 0.0f, 0.0f),
                                                new Animation(Animation.Type.SMOOTH, startAnimationDuration),
                                                null);
                                    }
                                    loadingLL.setVisibility(View.GONE);
                                    userLocationPlacemark.setGeometry(new Point(location.getLatitude(),
                                            location.getLongitude()));
                                    userLocation = new CustomPoint(location);
                                    userLocationPlacemark.setVisible(true);
                                } else {
                                    mapView.getMap().move(
                                            new CameraPosition(new Point(startLat, startLon),
                                                    startZoom, 0.0f, 0.0f),
                                            new Animation(Animation.Type.SMOOTH, startAnimationDuration),
                                            null);
                                }
                            })
                            .addOnFailureListener(e -> {
                                if (!finalMapMoved) {
                                    mapView.getMap().move(
                                            new CameraPosition(new Point(startLat, startLon),
                                                    startZoom, 0.0f, 0.0f),
                                            new Animation(Animation.Type.SMOOTH, startAnimationDuration),
                                            null);
                                }
                                mViewModel.visibilityOfLoadingLD.setValue(false);
                            });
                }
            } else {
                mapView.getMap().move(
                        new CameraPosition(new Point(startLat, startLon),
                                startZoom, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, startAnimationDuration),
                        null);
                mViewModel.visibilityOfLoadingLD.setValue(false);
                Toast.makeText(getContext(), "Включите геолокацию для лучшей работы приложения", Toast.LENGTH_SHORT).show();
            }

        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private void initUserLocation() {
        MapKit mapKit = MapKitFactory.getInstance();
        ImageProvider imageProvider = ImageProvider.fromResource(
                getActivity(), R.drawable.user_location_icon);
        userLocationPlacemark = mapView.getMap().getMapObjects().addPlacemark(new Point(startLat, startLon));
        userLocationPlacemark.setIcon(imageProvider, new IconStyle().setAnchor(
                        new PointF(0.5f, 0.8f)).
                setRotationType(RotationType.NO_ROTATION)
                .setZIndex(0.5f)
                .setScale(0.75f));
        userLocationPlacemark.setVisible(false);
        if (userLocation != null) mViewModel.visibilityOfLoadingLD.setValue(false);

        mapKit.createLocationManager().subscribeForLocationUpdates(0, 0, 0, true, FilteringMode.ON, new LocationListener() {
            @Override
            public void onLocationUpdated(@NonNull Location location) {
                mViewModel.visibilityOfLoadingLD.setValue(false);
                userLocationPlacemark.setGeometry(location.getPosition());
                userLocationPlacemark.setVisible(true);
                userLocation = new CustomPoint(location.getPosition());
            }

            @Override
            public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {

            }
        });
    }

    private void init() {
        mapView = rootView.findViewById(R.id.mapview);
        clusterizedCollection = mapView.getMap().getMapObjects().addClusterizedPlacemarkCollection(this);
        fragmentManager = requireActivity().getSupportFragmentManager();
        zoomPlusBTN = rootView.findViewById(R.id.zoomPlusBTN);
        zoomMinusBTN = rootView.findViewById(R.id.zoomMinusBTN);
        locationBTN = rootView.findViewById(R.id.locationBTN);
        loadingLL = rootView.findViewById(R.id.loadingLL);
        mapObjects = mapView.getMap().getMapObjects().addCollection();
        fusedLocationClientHuawei = com.huawei.hms.location.LocationServices.getFusedLocationProviderClient(getContext());
        mapWindow = mapView.getMapWindow();
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                result -> {
                    if (result) {
                        moveMap(true);
                    } else {
                        Toast.makeText(getContext(), "Дайте доступ к геолокации", Toast.LENGTH_SHORT).show();
                        mapView.getMap().move(
                                new CameraPosition(new Point(startLat, startLon),
                                        startZoom, 0.0f, 0.0f),
                                new Animation(Animation.Type.SMOOTH, startAnimationDuration),
                                null);
                        mViewModel.visibilityOfLoadingLD.setValue(false);
                    }
                }
        );
    }

    private void addPointOnMap(Mark mark) {
        ImageProvider imageProvider = ImageProvider.fromResource(requireContext(), R.drawable.map_pin);
        PlacemarkMapObject mapObject = clusterizedCollection.addPlacemark(new Point(mark.getLatitude(), mark.getLongitude()));
        mapObject.setUserData(mark);
        mapObject.setIcon(imageProvider, new IconStyle().setAnchor(
                        new PointF(0.5f, 1f)).
                setRotationType(RotationType.NO_ROTATION)
                .setZIndex(0f)
                .setScale(0.65f));
        mapObject.addTapListener(MapFragment.this);
    }


    @Override
    public boolean onClusterTap(@NonNull Cluster cluster) {
        mapView.getMap().move(
                new CameraPosition(cluster.getAppearance().getGeometry(), mapView.getMap().getCameraPosition().getZoom() + 5, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, (float) 0.8),
                null);
        return false;
    }

    private void initListeners() {
        mViewModel.marks.setValue(new ArrayList<>());
        zoomPlusBTN.setOnClickListener(view -> mapView.getMap().move(
                new CameraPosition(mapView.getMap().getCameraPosition().getTarget(), mapView.getMap().getCameraPosition().getZoom() + 1, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, (float) 0.1),
                null));
        zoomMinusBTN.setOnClickListener(view -> mapView.getMap().move(
                new CameraPosition(mapView.getMap().getCameraPosition().getTarget(),
                        mapView.getMap().getCameraPosition().getZoom() - 1,
                        0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, (float) 0.1),
                null));

        locationBTN.setOnClickListener(view -> {
            getCurrentLocation();
        });

        mViewModel.marks.observe(getViewLifecycleOwner(), this::drawPointsOnMap);

        getParentFragmentManager().setFragmentResultListener("markKey", getViewLifecycleOwner(), (requestKey, result) -> {
            Mark mark = (Mark) result.getSerializable("mark");
            mapView.getMap().move(
                    new CameraPosition(new Point(mark.getLatitude(), mark.getLongitude()), 18f, 0.0f, 0.0f),
                    new Animation(Animation.Type.SMOOTH, (float) 0.8),
                    null);
        });
        getParentFragmentManager().setFragmentResultListener("filters", getViewLifecycleOwner(), (requestKey, result) ->
                mViewModel.filters.setValue(result));
        mapView.getMap().addCameraListener(listener);
        mViewModel.visibilityOfLoadingLD.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean)
                loadingLL.setVisibility(View.VISIBLE);
            else {
                loadingLL.setVisibility(View.GONE);
            }
        });


    }

    @SuppressWarnings("unchecked")
    private void drawPointsOnMap(ArrayList<Mark> marks) {
        clusterizedCollection.clear();
        for (Mark mark : marks) {
            addPointOnMap(mark);
        }
        clusterizedCollection.clusterPlacemarks(70, 20);
    }

    private void getCurrentLocation() {
        if (userLocationPlacemark != null && userLocation != null) {
            mapView.getMap().move(
                    new CameraPosition(userLocationPlacemark.getGeometry(), 18f, 0.0f, 0.0f),
                    new Animation(Animation.Type.SMOOTH, (float) 0.1),
                    null);
            return;
        }
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isGPSEnabled()) {
                moveMap(true);
            } else {
                Toast.makeText(getContext(), "Включите геолокацию для лучшей работы приложения", Toast.LENGTH_SHORT).show();
            }

        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }


    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled;

        if (locationManager == null) {
            locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
        try {
            Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_mark);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.setCancelable(true);
            Mark mark = (Mark) mapObject.getUserData();
            TextView aboutTV = dialog.findViewById(R.id.aboutTV);

            aboutTV.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("mark", mark);
                dialog.cancel();
                //Navigation.findNavController(getActivity(), R.id.host_fragment).navigate(R.id.eventInformationFragment, bundle);
            });

            if (mark != null) {

            }
            if (getActivity() != null && !getActivity().isFinishing()) {
                dialog.show();
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void buildRoute(Point fromPoint, Point toPoint) {
        DrivingRouter drivingRouter = DirectionsFactory.getInstance().createDrivingRouter();
        DrivingOptions drivingOptions = new DrivingOptions();
        drivingOptions.setRoutesCount(1);
        VehicleOptions vehicleOptions = new VehicleOptions();
        vehicleOptions.setVehicleType(VehicleType.DEFAULT);
        ArrayList<RequestPoint> points = new ArrayList<>();
        points.add(new RequestPoint(fromPoint, RequestPointType.WAYPOINT, null, null));
        points.add(new RequestPoint(toPoint, RequestPointType.WAYPOINT, null, null));
        drivingRouter.requestRoutes(
                points,
                drivingOptions,
                vehicleOptions,
                this
        );


    }


    @Override
    public void onStop() {
        super.onStop();
        MapKitFactory.getInstance().onStop();
        mapView.onStop();
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    private boolean areGoogleServicesAvailable() {
        int availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());
        return availability == ConnectionResult.SUCCESS;

    }

    @Override
    public void onDrivingRoutes(List<DrivingRoute> routes) {
        for (DrivingRoute route : routes) {
            mapObjects.addPolyline(route.getGeometry());
        }
    }

    @Override
    public void onDrivingRoutesError(@NonNull Error error) {

    }

    @Override
    public void onClusterAdded(Cluster cluster) {
        cluster.getPlacemarks().get(0).getDirection();
        cluster.getAppearance().setIcon(new TextImageProvider(requireContext(), Integer.toString(cluster.getSize())));
        cluster.addClusterTapListener(this);
    }
}