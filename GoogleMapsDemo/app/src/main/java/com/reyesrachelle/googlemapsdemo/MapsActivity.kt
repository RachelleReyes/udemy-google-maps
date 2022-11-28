package com.reyesrachelle.googlemapsdemo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.reyesrachelle.googlemapsdemo.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraAndViewport() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, map)
        return true
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val losAngeles = LatLng(34.05447469544268, -118.2380027484249)
        val losAngeles2 = LatLng(34.09657501898418, -118.22544035564495)
        val newYork = LatLng(40.71312102585187, -73.99492995795815)
        val losAngelesMarker =
            map.addMarker(
                MarkerOptions()
                    .position(losAngeles)
                    .title("Marker in Los Angeles")
            )
        val losAngelesMarker2 =
            map.addMarker(
                MarkerOptions()
                    .position(losAngeles2)
                    .title("Marker in Los Angeles")
                    .zIndex(1f)
            )

        // There are 20 zoom levels
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 10f))
        map.uiSettings.apply {
            isZoomControlsEnabled = true // This shows buttons
        }

        typeAndStyle.setMapStyle(map, this)

//    lifecycleScope.launch {
//            delay(4000L)
//        map.setOnMarkerClickListener(this)
//    }

    }


}