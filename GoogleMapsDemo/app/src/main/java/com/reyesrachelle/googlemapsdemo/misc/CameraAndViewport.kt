package com.reyesrachelle.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds


class CameraAndViewport {
    val losAngeles: CameraPosition = CameraPosition.Builder()
        .target(LatLng(34.05447469544268, -118.2380027484249))
        .zoom(17f)
        .bearing(0f)
        .tilt(45f)
        .build()

    val melbourneBounds = LatLngBounds(
        LatLng(-38.62435088872601, 144.06424014552496), //SW
        LatLng(-37.244510861909156, 146.1269230452603) //NE
    )
}