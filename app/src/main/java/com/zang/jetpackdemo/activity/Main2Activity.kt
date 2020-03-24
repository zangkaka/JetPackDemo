package com.zang.jetpackdemo.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.zang.jetpackdemo.MyLocationManager
import com.zang.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private lateinit var myLocationManager: MyLocationManager

    companion object {
        const val REQUEST_LOCATION_PERMISSION_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION_CODE
            )
        } else {
//            setUpLocation()
        }

        main2_next_btn.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setUpLocation()
        } else {
            Toast.makeText(this, "You do not access GPS", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpLocation() {
        myLocationManager =
            MyLocationManager(this) { location ->
                main2_location_txt.text = location.latitude.toString() + " , " + location.longitude
            }
        lifecycle.addObserver(myLocationManager)
    }
}
