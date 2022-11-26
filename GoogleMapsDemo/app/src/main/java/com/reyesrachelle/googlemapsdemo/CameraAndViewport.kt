package com.reyesrachelle.googlemapsdemo

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng


class CameraAndViewport {
    val losAngeles: CameraPosition = CameraPosition.Builder()
        .target(LatLng(34.05447469544268, -118.2380027484249))
        .zoom(17f)
        .bearing(0f)
        .tilt(45f)
        .build()
}