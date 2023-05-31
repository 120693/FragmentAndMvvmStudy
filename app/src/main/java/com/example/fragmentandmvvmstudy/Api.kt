package com.example.fragmentandmvvmstudy

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    suspend fun getWeatherByCityName(@Query("q") cityName: String, @Query("appid") apiKey: String) : Response<WeatherModel>
}