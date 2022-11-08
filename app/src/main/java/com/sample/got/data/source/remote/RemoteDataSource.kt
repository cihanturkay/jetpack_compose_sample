package com.sample.got.data.source.remote

import com.sample.got.data.model.Character
import com.sample.got.data.model.House
import com.sample.got.data.model.Result
import com.sample.got.data.source.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(
    private val api: Api
) : DataSource {

    override fun getHousesStream(page:Int, pageSize:Int): Flow<Result<List<House>>> = flow {
        emit(getHouses(page, pageSize))
    }

    override suspend fun getHouses(page:Int, pageSize:Int): Result<List<House>> {
        val response = try {
            api.getHouses(page,pageSize)
        } catch (e: Exception) {
            return Result.Error(e)
        }

        return Result.Success(response)
    }

    override suspend fun getHouse(id: Int): Result<House> {
        val response = try {
            api.getHouse(id)
        } catch (e: Exception) {
            return Result.Error(e)
        }

        return Result.Success(response)
    }

    override suspend fun getCharacter(id: Int): Result<Character> {
        val response = try {
            api.getCharacter(id)
        } catch (e: Exception) {
            return Result.Error(e)
        }

        return Result.Success(response)
    }


}