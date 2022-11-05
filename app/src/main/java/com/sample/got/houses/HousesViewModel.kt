package com.sample.got.houses

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.sample.got.data.model.House
import com.sample.got.data.repo.GOTRepository
import com.sample.got.data.repo.PagedHousesSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

data class HousesUiState(
    val items: List<House> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)

@HiltViewModel
class HousesViewModel @Inject constructor(
    private val GOTRepository: GOTRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _isError = MutableStateFlow(false)

    val houses: Flow<PagingData<House>> = Pager(PagingConfig(pageSize = 40)) {
        PagedHousesSource(GOTRepository)
    }.flow.cachedIn(viewModelScope)
}