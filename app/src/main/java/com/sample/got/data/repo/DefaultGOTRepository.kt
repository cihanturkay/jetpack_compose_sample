package com.sample.got.data.repo

import com.sample.got.data.model.House
import com.sample.got.data.model.Result
import com.sample.got.data.source.GOTDataSource
import kotlinx.coroutines.flow.Flow

class DefaultGOTRepository(
    private val GOTRemoteDataSource: GOTDataSource,
) : GOTRepository {

    override fun getHouses(): Flow<Result<List<House>>> {
        // TODO: With given interface, it is possible to create local data source and cache the
        //  remote data, for now let's keep it simple and use only remote data source.
        return GOTRemoteDataSource.getHousesStream()
    }

    override fun getHouse(id: Int): Flow<Result<House>> {
        TODO("Not yet implemented")
    }
}