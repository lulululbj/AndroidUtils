package luyao.androidutils

import android.Manifest
import android.annotation.SuppressLint
import android.location.Address
import android.location.Criteria
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_location.*
import luyao.lib.LocationUtil

/**
 * Created by luyao
 * on 2018/10/11 15:56
 */
class LocationActivity : AppCompatActivity(), LocationUtil.OnLocationListener {


    private val locationUtil by lazy {
        LocationUtil.Builder(this)
                .accuracy(Criteria.ACCURACY_FINE)
                .minTime(10 * 1000)
                .minDistance(200f)
                .altitudeRequired(true)
                .costAllowed(true)
                .locationListener(this)
                .build()
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        RxPermissions(this)
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe { granted -> if (granted) locationUtil.startLocation() }
    }

    @SuppressLint("SetTextI18n")
    override fun getAddress(address: Address?) {
        locationTv.text = "${address?.countryName}\n${address?.getAddressLine(0)}"
    }

    override fun onLocationError(message: String?) {

    }

}