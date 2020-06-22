package com.nightmareinc.snapptest.model.api

import com.google.gson.GsonBuilder
import com.nightmareinc.snapptest.listener.Constant
import com.nightmareinc.snapptest.model.models.VehicleList
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface VehicleAPI {

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): VehicleAPI {

            val gson = GsonBuilder().setLenient().create()

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(VehicleAPI::class.java)

        }
    }

    @GET(Constant.VEHICLES_LIST)
    suspend fun getAllVehicles() : Response<VehicleList>

}