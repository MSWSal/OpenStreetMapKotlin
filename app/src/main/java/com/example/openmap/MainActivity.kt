package com.example.openmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MainActivity : AppCompatActivity() {
    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        // Initialize OSMdroid
        Configuration.getInstance().load(this, getSharedPreferences("OSM_PREFS", MODE_PRIVATE))

        // Get MapView from layout
        map = findViewById(R.id.map)
        map.setMultiTouchControls(true)

        // Set default zoom level and location
        val mapController = map.controller
        mapController.setZoom(15.0)
        val startPoint = GeoPoint(7.9541, 80.7547) // Example: Eiffel Tower, Paris
        mapController.setCenter(startPoint)

        // Add a marker to the map
        val startMarker = Marker(map)
        startMarker.position = startPoint
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        startMarker.title = "Eiffel Tower"
        map.overlays.add(startMarker)
    }

    override fun onResume() {
        super.onResume()
        map.onResume() // needed for compass, my location overlays, etc.
    }

    override fun onPause() {
        super.onPause()
        map.onPause() // needed for compass, my location overlays, etc.
    }

}