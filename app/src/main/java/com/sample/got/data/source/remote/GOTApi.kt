package com.sample.got.data.source.remote

import com.sample.got.data.model.House
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface GOTApi {

    @GET("houses")
    suspend fun getHouses(): List<House>

    @GET("house/{id}")
    suspend fun getHouse(@Path("id") id: Int): House
}