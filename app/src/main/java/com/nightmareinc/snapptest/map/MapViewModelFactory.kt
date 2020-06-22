package com.nightmareinc.snapptest.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nightmareinc.snapptest.model.repositories.VehiclesRepository
import java.lang.IllegalArgumentException

class MapViewModelFactory (var vehiclesRepository: VehiclesRepository) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(vehiclesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewMode Class")
    }

}