package com.nightmareinc.snapptest.model.repositories

import com.nightmareinc.snapptest.model.api.SafeApiRequest
import com.nightmareinc.snapptest.model.api.VehicleAPI
import com.nightmareinc.snapptest.model.models.VehicleList

class VehiclesRepository(private val vehicleAPI: VehicleAPI) : SafeApiRequest() {

    suspend fun fetchVehicles(): VehicleList {
        val response = apiRequest { vehicleAPI.getAllVehicles() }
        return response
    }

}