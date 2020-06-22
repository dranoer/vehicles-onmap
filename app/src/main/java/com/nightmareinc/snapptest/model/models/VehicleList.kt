package com.nightmareinc.snapptest.model.models

import com.google.gson.annotations.SerializedName

data class VehicleList(
    @field:SerializedName("vehicles")
    val vehicles: List<Vehicle>
)