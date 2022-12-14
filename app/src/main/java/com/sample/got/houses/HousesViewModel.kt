package com.sample.got.houses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.got.data.model.House
import com.sample.got.data.repo.Repository
import com.sample.got.data.repo.PagedHousesSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HousesViewModel @Inject constructor(
    private val Repository: Repository,
) : ViewModel() {
    val houses: Flow<PagingData<House>> = Pager(PagingConfig(pageSize = 40)) {
            PagedHousesSource(Repository)
        }.flow.cachedIn(viewModelScope)
}