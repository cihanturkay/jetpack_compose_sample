package com.sample.got.data.source.remote

import com.sample.got.data.model.House
import com.sample.got.data.model.Result
import com.sample.got.data.source.GOTDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GOTRemoteDataSource(
    private val GOTApi: GOTApi
) : GOTDataSource {

    override fun getHousesStream(page:Int, pageSize:Int): Flow<Result<List<House>>> = flow {
        emit(getHouses(page, pageSize))
    }

    override suspend fun getHouses(page:Int, pageSize:Int): Result<List<House>> {
        val response = try {
            GOTApi.getHouses(page,pageSize)
        } catch (e: Exception) {
            return Result.Error(e)
        }

        return Result.Success(response)
    }

    override suspend fun getHouse(id: Int): Result<House> {
        val response = try {
            GOTApi.getHouse(id)
        } catch (e: Exception) {
            return Result.Error(e)
        }

        return Result.Success(response)
    }


}