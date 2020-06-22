package com.nightmareinc.snapptest.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nightmareinc.snapptest.R
import com.nightmareinc.snapptest.databinding.FragmentMapsBinding
import com.nightmareinc.snapptest.model.api.NetworkConnectionInterceptor
import com.nightmareinc.snapptest.model.api.VehicleAPI
import com.nightmareinc.snapptest.model.repositories.VehiclesRepository


class MapsFragment : Fragment() {

    lateinit var binding: FragmentMapsBinding

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_maps, container, false)

//        val application = requireNotNull(this.activity).application

        val viewModelFactory = MapViewModelFactory(VehiclesRepository(VehicleAPI.invoke(
            NetworkConnectionInterceptor(context)
        )))

        val mapViewModel = ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)

        binding.mapViewModel = mapViewModel
        binding.lifecycleOwner = this

        binding.map.postDelayed({
            binding.map.onCreate(Bundle())
            binding.map.getMapAsync {
//                onMapReady(it)
//                binding.map.getMapAsync(callback)
            }
        }, 500)

        return binding.root
    }

    override fun onResume() {
        binding.map.postDelayed({
            binding.map.onResume()
        }, 3000)


        super.onResume()
    }

    override fun onDestroy() {
        binding.map.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        binding.map.onLowMemory()
        super.onLowMemory()
    }
}