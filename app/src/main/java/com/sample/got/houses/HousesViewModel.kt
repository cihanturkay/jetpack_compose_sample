package com.sample.got.houses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.got.data.model.House
import com.sample.got.data.repo.GOTRepository
import com.sample.got.data.repo.PagedHousesSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HousesViewModel @Inject constructor(
    private val GOTRepository: GOTRepository,
) : ViewModel() {
    val houses: Flow<PagingData<House>> =
        Pager(PagingConfig(pageSize = 40, prefetchDistance = 80)) {
            PagedHousesSource(GOTRepository)
        }.flow.cachedIn(viewModelScope)
}