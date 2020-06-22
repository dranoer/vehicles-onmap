package com.nightmareinc.snapptest.model.models

import com.google.gson.annotations.SerializedName

data class Vehicle (

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("lat")
    val lat: Double? = null,

    @field:SerializedName("lng")
    val lng: Double? = null,

    @field:SerializedName("bearing")
    val bearing: Int? = null,

    @field:SerializedName("image_url")
    val image_url: String? = null


)