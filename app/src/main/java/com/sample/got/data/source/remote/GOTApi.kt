package com.sample.got.data.source.remote

import com.sample.got.data.model.Character
import com.sample.got.data.model.House
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface GOTApi {

    @GET("houses")
    suspend fun getHouses(@Query("page") page: Int, @Query("pageSize") pageSize: Int): List<House>

    @GET("houses/{id}")
    suspend fun getHouse(@Path("id") id: Int): House

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character
}