package luyao.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.location.Criteria.ACCURACY_FINE;
import static android.location.Criteria.NO_REQUIREMENT;

/**
 * Created by luyao
 * on 2018/10/11 13:47
 */
public class LocationUtil {

    private Context mContext;
    private int mAccuracy;
    private int mHorizontalAccuracy;
    private int mVerticalAccuracy;
    private int mSpeedAccuracy;
    private int mBearingAccuracy;
    private int mPowerRequirement;
    private boolean mAltitudeRequired;
    private boolean mBearingRequired;
    private boolean mSpeedRequired;
    private boolean mCostAllowed;
    private long minTime;
    private float minDistance;
    private LocationManager locationManager;
    private OnLocationListener locationListener;
    private MyLocationListener myLocationListener;

    private LocationUtil(Builder builder) {
        this.mContext = builder.mContext;
        this.mAccuracy = builder.accuracy;
        this.mHorizontalAccuracy = builder.mHorizontalAccuracy;
        this.mVerticalAccuracy = builder.mVerticalAccuracy;
        this.mSpeedAccuracy = builder.mSpeedAccuracy;
        this.mBearingAccuracy = builder.mBearingAccuracy;
        this.mPowerRequirement = builder.mPowerRequirement;
        this.mAltitudeRequired = builder.mAltitudeRequired;
        this.mBearingRequired = builder.mBearingRequired;
        this.mSpeedRequired = builder.mSpeedRequired;
        this.mCostAllowed = builder.mCostAllowed;
        this.minTime = builder.minTime;
        this.minDistance = builder.minDistance;
        this.locationListener = builder.locationListener;
        this.locationManager = builder.locationManager;
    }

    @SuppressLint("MissingPermission")
    public void startLocation() {
        Criteria criteria = new Criteria();
        criteria.setSpeedRequired(mSpeedRequired);
        criteria.setPowerRequirement(mPowerRequirement);
        criteria.setCostAllowed(mCostAllowed);
        criteria.setBearingRequired(mBearingRequired);
        criteria.setAltitudeRequired(mAltitudeRequired);
        criteria.setAccuracy(mAccuracy);
        criteria.setHorizontalAccuracy(mHorizontalAccuracy);
        criteria.setVerticalAccuracy(mVerticalAccuracy);
        criteria.setBearingAccuracy(mBearingAccuracy);
        criteria.setSpeedAccuracy(mSpeedAccuracy);

        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null || (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))) {
            if (locationListener != null) locationListener.onLocationError("定位不可用");
            return;
        }

        String provider = locationManager.getBestProvider(criteria, true);
        Location lastKnownLocation = locationManager.getLastKnownLocation(provider);
        if (lastKnownLocation != null)
            locationListener.getAddress(getAddress(mContext, lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()));

        if (myLocationListener == null) myLocationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(provider, minTime, minDistance, myLocationListener);
    }

    @SuppressLint("MissingPermission")
    public void stopLocation() {
        if (locationManager != null) {
            if (myLocationListener != null) {
                locationManager.removeUpdates(myLocationListener);
                myLocationListener = null;
            }
            locationManager = null;
        }
    }

    private Address getAddress(Context c, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(c, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            return addressList == null || addressList.size() == 0 ? null : addressList.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface OnLocationListener {

        void onLocationError(String message);
////
////        void getLastKnownLocation(Location location);
////
////        void onLocationChanged(Location location);
////
////        void onStatusChanged(String s, int i, Bundle bundle);

        void getAddress(Address address);
    }

    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (locationListener != null) {
//                locationListener.onLocationChanged(location);
                locationListener.getAddress(getAddress(mContext, location.getLatitude(), location.getLongitude()));
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
//            if (locationListener != null) locationListener.onStatusChanged(s, i, bundle);
        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }

    public static class Builder {
        private Context mContext;
        private int accuracy = ACCURACY_FINE;
        private int mHorizontalAccuracy = NO_REQUIREMENT;
        private int mVerticalAccuracy = NO_REQUIREMENT;
        private int mSpeedAccuracy = NO_REQUIREMENT;
        private int mBearingAccuracy = NO_REQUIREMENT;
        private int mPowerRequirement = NO_REQUIREMENT;
        private boolean mAltitudeRequired = false;
        private boolean mBearingRequired = false;
        private boolean mSpeedRequired = false;
        private boolean mCostAllowed = false;
        private long minTime = 10 * 1000;
        private float minDistance = 20f;
        private LocationManager locationManager;
        private OnLocationListener locationListener;

        public Builder(Context context) {
            this.mContext = context;
            locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        }

        public Builder accuracy(int accuracy) {
            this.accuracy = accuracy;
            return this;
        }

        public Builder horizontalAccuracy(int horizontalAccuracy) {
            this.mHorizontalAccuracy = horizontalAccuracy;
            return this;
        }

        public Builder verticalAccuracy(int verticalAccuracy) {
            this.mVerticalAccuracy = verticalAccuracy;
            return this;
        }

        public Builder speedAccuracy(int speedAccuracy) {
            this.mSpeedAccuracy = speedAccuracy;
            return this;
        }

        public Builder bearingAccuracy(int bearingAccuracy) {
            this.mBearingAccuracy = bearingAccuracy;
            return this;
        }

        public Builder powerRequirement(int powerRequirement) {
            this.mPowerRequirement = powerRequirement;
            return this;
        }

        public Builder altitudeRequired(boolean altitudeRequired) {
            this.mAltitudeRequired = altitudeRequired;
            return this;
        }

        public Builder bearingRequired(boolean bearingRequired) {
            this.mBearingRequired = bearingRequired;
            return this;
        }

        public Builder speedRequired(boolean speedRequired) {
            this.mSpeedRequired = speedRequired;
            return this;
        }

        public Builder costAllowed(boolean costAllowed) {
            this.mCostAllowed = costAllowed;
            return this;
        }

        public Builder minTime(long minTime) {
            this.minTime = minTime;
            return this;
        }

        public Builder minDistance(float minDistance) {
            this.minDistance = minDistance;
            return this;
        }

        public Builder locationListener(OnLocationListener locationListener) {
            this.locationListener = locationListener;
            return this;
        }

        public LocationUtil build() {
            return new LocationUtil(this);
        }
    }
}
