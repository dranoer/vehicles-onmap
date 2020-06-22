package com.nightmareinc.snapptest.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.snapptest.model.models.VehicleList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import com.nightmareinc.snapptest.model.repositories.VehiclesRepository
import com.nightmareinc.snapptest.util.Status
import java.lang.Exception

class MapViewModel (var vehiclesRepository: VehiclesRepository) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var vehicleList: VehicleList? = null

    private var _vehicles = MutableLiveData<VehicleList>()
    val vehicles: LiveData<VehicleList>?
        get() = _vehicles

    private val _status = MutableLiveData<Status>()
    val status : LiveData<Status>
        get() = _status


    init {
        getVehicles()
    }


    private fun getVehicles() {
        uiScope.launch {
            try {
                vehicleList = vehiclesRepository.fetchVehicles()
                _status.value = Status.LOADING
                _vehicles.value = vehicleList
                _status.value = Status.DONE
                Log.d("nzn", "fetch all vehicles >> ${_vehicles?.value}")
            } catch (e: Exception){
                _status.value = Status.ERROR
                Log.d("nzn", "error >> ${_status?.value} >>> ${e.message}")
                e.printStackTrace()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}