package com.example.weather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.logic.Repository
import com.example.weather.logic.model.Place
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap


//class PlaceViewModel : ViewModel() {
//    private val searchLiveData = MutableLiveData<String>()
//
//    val placeList = ArrayList<Place>()
//
//    val placeLiveData = searchLiveData.switchMap { query ->
//        liveData {
//            val places = Repository.searchPlaces(query)
//            emit(places)
//        }
//    }
//
//    fun searchPlaces(query: String) {
//        searchLiveData.value = query
//    }
//}
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiveData = searchLiveData.switchMap { query ->
        Repository.searchPlaces(query)
    }
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}