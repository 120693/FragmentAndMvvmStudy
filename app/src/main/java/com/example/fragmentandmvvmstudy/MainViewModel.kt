package com.example.fragmentandmvvmstudy

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    // Generic을 사용해 관찰하고자 하는 데이터의 타입(Type)을 갖는 LiveData 인스턴스를 생성
    // View와 ViewModel을 분리하기 위하여
    private var _weatherData = MutableLiveData<WeatherModel?>()
    var weatherData: LiveData<WeatherModel?> = _weatherData

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
        }

    // ViewModel 안에서는 lifecycleScope 대신에 viewModelScope을 사용해야 한다.
    fun updateWeatherData(cityName: String, apiKey: String) {
        viewModelScope.launch {
            val result = api.getWeatherByCityName(cityName, apiKey)
            val weatherModel = result.body()
            _weatherData.value = weatherModel

        }
    }
}