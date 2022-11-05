package com.sample.got.data.source

import com.sample.got.data.model.Result
import com.sample.got.data.model.House
import kotlinx.coroutines.flow.Flow

/**
 * Main entry point for accessing data (remote & local).
 */
interface GOTDataSource {

    fun getHousesStream(page:Int, pageSize:Int): Flow<Result<List<House>>>

    suspend fun getHouses(page:Int, pageSize:Int): Result<List<House>>

    suspend fun getHouse(id: Int): Result<House>
}