package com.zang.jetpackdemo

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class MyLocationManager(private val context: Context, private val callBack: (Location) -> Unit): LifecycleObserver {
    private var mLocationManager: LocationManager? = null
    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            callBack.invoke(location!!)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun start() {
        mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mLocationManager!!.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener
        )
        val lastLocation = mLocationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (lastLocation != null) {
            locationListener.onLocationChanged(lastLocation)
        }
        Toast.makeText(context, "MyLocationManager started", Toast.LENGTH_SHORT).show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stop() {
        if (mLocationManager != null) {
            return
        }
        mLocationManager!!.removeUpdates(locationListener)
        mLocationManager = null
        Toast.makeText(context, "MyLocationManager paused", Toast.LENGTH_SHORT).show()
    }

}