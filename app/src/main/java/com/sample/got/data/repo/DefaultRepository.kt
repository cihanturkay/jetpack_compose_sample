package com.sample.got.data.repo

import com.sample.got.data.model.House
import com.sample.got.data.model.Result
import com.sample.got.data.source.DataSource
import com.sample.got.data.model.Character
import java.lang.Exception

class DefaultRepository(
    private val remoteDataSource: DataSource,
) : Repository {

    // TODO: With given interface, it is possible to create local data source and cache the
    //  remote data, for now let's keep it simple and use only remote data source.

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>> {
        return remoteDataSource.getHouses(page, pageSize)
    }

    override suspend fun getHouse(id: Int): Result<House> {
        return remoteDataSource.getHouse(id)
    }

    override suspend fun getCharacters(characterIds: List<Int>): Result<List<Character>>  {
        val characters = mutableListOf<Character>()
        var isSuccessful = true

        for (id in characterIds) {
            val result = remoteDataSource.getCharacter(id)
            if (result is Result.Success) {
                characters.add(result.data)
            } else {
                isSuccessful = false
            }
        }

        return if(!isSuccessful && characters.isEmpty()) {
            Result.Error(Exception("Failed to load characters"))
        } else {
            Result.Success(characters)
        }
    }
}