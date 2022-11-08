package com.sample.got.data.source

import com.sample.got.data.model.Character
import com.sample.got.data.model.House
import com.sample.got.data.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Main entry point for accessing data (remote & local).
 */
interface DataSource {

    fun getHousesStream(page: Int, pageSize: Int): Flow<Result<List<House>>>

    suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>>

    suspend fun getHouse(id: Int): Result<House>

    suspend fun getCharacter(id: Int): Result<Character>
}