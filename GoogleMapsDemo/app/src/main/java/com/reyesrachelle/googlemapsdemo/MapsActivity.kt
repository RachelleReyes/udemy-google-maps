package com.reyesrachelle.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.reyesrachelle.googlemapsdemo.databinding.ActivityMapsBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener, OnMarkerDragListener {

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
        val newYork = LatLng(40.71312102585187, -73.99492995795815)
        val losAngelesMarker = map.addMarker(MarkerOptions().position(losAngeles).title("Marker in Los Angeles").draggable(true))
        losAngelesMarker?.tag = "Restaurant"
        // There are 20 zoom levels
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 10f))
//        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.losAngeles))

        map.uiSettings.apply {
            isZoomControlsEnabled = true // This shows buttons
        }

        typeAndStyle.setMapStyle(map, this)

        map.setOnMarkerDragListener(this)

//        lifecycleScope.launch {
//            delay(4000L)
//            losAngelesMarker?.remove()
//            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.losAngeles), 2000, object: GoogleMap.CancelableCallback {
//                override fun onFinish() {
//                    Toast.makeText(this@MapsActivity, "Finished", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onCancel() {
//                    Toast.makeText(this@MapsActivity, "Cancelled", Toast.LENGTH_SHORT).show()
//                }
//            })
//            map.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)
//            map.animateCamera(CameraUpdateFactory.scrollBy(2000f, 0f), 2000, null)
//            map.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds, 100), 2000, null)
//            map.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)
        }

//        onMapClicked()
//        onMapLongClicked()
//
//        map.setOnMarkerClickListener(this)
//    }

    private fun onMapClicked() {
        map.setOnMapClickListener {
            Toast.makeText(this, "Single click", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onMapLongClicked() {
        map.setOnMapLongClickListener {
            map.addMarker(MarkerOptions().position(it).title("New Marker"))
            Toast.makeText(this, "${it.longitude} ${it.latitude}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker!=null) {
            Log.d("Marker", marker.tag as String)
        } else {
            Log.d("Marker", "Empty")
        }
        return true // Returning true doesn't show the info window in the marker
    }

    override fun onMarkerDrag(p0: Marker) {
        Log.d("Drag", "Drag")
    }

    override fun onMarkerDragEnd(p0: Marker) {
        Log.d("Drag", "End")
    }

    override fun onMarkerDragStart(p0: Marker) {
        Log.d("Drag", "Start")
    }
}