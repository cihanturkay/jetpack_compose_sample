package com.sample.got.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.got.data.model.House
import com.sample.got.data.model.Result

class PagedHousesSource(
    private val GOTRepository: GOTRepository
) : PagingSource<Int, House>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, House> {
        return try {
            val nextPage = params.key ?: 1
            when (val houses = GOTRepository.getHouses(nextPage)) {
                is Result.Success -> {
                    LoadResult.Page(
                        data = houses.data,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = if (houses.data.isEmpty()) null else nextPage + 1
                    )
                }
                is Result.Error -> {
                    LoadResult.Error(houses.exception)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, House>): Int? {
        return state.anchorPosition
    }
}