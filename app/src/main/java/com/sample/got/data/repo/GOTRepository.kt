package com.sample.got.data.repo

import com.sample.got.data.model.Character
import com.sample.got.data.model.House
import com.sample.got.data.model.Result

/**
 * Single source of truth for ui state
 */
interface GOTRepository {
    suspend fun getHouses(page: Int, pageSize: Int = 40): Result<List<House>>
    suspend fun getHouse(id: Int): Result<House>
    suspend fun getCharacters(characterIds: List<Int>): Result<List<Character>>
}