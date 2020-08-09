package net.bedev.jentikapps.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiConfig {

    fun getApiService (): ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://jentik.bimar.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}