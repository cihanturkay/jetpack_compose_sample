package com.sample.got.data.repo

import com.sample.got.data.model.House
import com.sample.got.data.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Single source of truth for ui state
 */
interface GOTRepository {
    fun getHouses(): Flow<Result<List<House>>>
    fun getHouse(id: Int): Flow<Result<House>>
}